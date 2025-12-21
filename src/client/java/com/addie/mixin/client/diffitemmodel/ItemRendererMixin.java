package com.addie.mixin.client.diffitemmodel;

import com.addie.Smored;
import com.lib.core.items.DifferingHandModelItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Final
    @Shadow
    private ItemModels models;

    /**
     * Always return the normal inventory model for DifferingHandModelItem.
     * This ensures the handheld model is never used in GUI, inventory, or on the ground.
     */
    @Inject(
            method = "getModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void smored$getInventoryModel(ItemStack stack, World world, LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if (!(stack.getItem() instanceof DifferingHandModelItem)) {
            return;
        }

        BakedModel baseModel = this.models.getModelManager().getModel(
                new ModelIdentifier(
                        Smored.MOD_ID,
                        Registries.ITEM.getId(stack.getItem()).getPath(),
                        "inventory"
                )
        );

        ClientWorld clientWorld = world instanceof ClientWorld cw ? cw : null;

        BakedModel resolved = baseModel.getOverrides().apply(baseModel, stack, clientWorld, entity, seed);

        cir.setReturnValue(resolved != null ? resolved : this.models.getModelManager().getMissingModel());
    }

    /**
     * Swap to the handheld model when rendering in hand (first- or third-person).
     * This replaces the normal model only for player hands.
     */
    @Inject(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void smored$swapHandModel(
            ItemStack stack,
            ModelTransformationMode mode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertices,
            int light,
            int overlay,
            BakedModel model,
            CallbackInfo ci
    ) {
        if (!(stack.getItem() instanceof DifferingHandModelItem)) {
            return;
        }

        // Only swap for first- or third-person hands
        if (mode == ModelTransformationMode.FIRST_PERSON_LEFT_HAND
                || mode == ModelTransformationMode.FIRST_PERSON_RIGHT_HAND
                || mode == ModelTransformationMode.THIRD_PERSON_LEFT_HAND
                || mode == ModelTransformationMode.THIRD_PERSON_RIGHT_HAND) {

            BakedModel handheld = this.models.getModelManager().getModel(
                    new ModelIdentifier(
                            Smored.MOD_ID,
                            "handheld_" + Registries.ITEM.getId(stack.getItem()).getPath(),
                            "inventory"
                    )
            );

            // Call the original renderItem with the handheld model
            ((ItemRenderer)(Object)this).renderItem(stack, mode, leftHanded, matrices, vertices, light, overlay, handheld);

            ci.cancel(); // cancel the original call so it doesn't render twice
        }
    }
}
