package com.addie.mixin.client;

import com.addie.core.item.MarshmallowOnAStickItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Inject(
            method = "renderFirstPersonItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void smored$applyRoastTransform(
            AbstractClientPlayerEntity player,
            float tickDelta,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack stack,
            float equipProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        if (!(stack.getItem() instanceof MarshmallowOnAStickItem)) return;
        if (!stack.hasNbt() || !stack.getNbt().getBoolean("Roasting")) return;
        if (!player.isUsingItem() || player.getActiveHand() != hand) return;

        ci.cancel();
        matrices.push();

        matrices.translate(0f, -0.35f, -0.8f);

        Arm arm = (hand == Hand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();
        ModelTransformationMode mode = (arm == Arm.RIGHT)
                ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND
                : ModelTransformationMode.FIRST_PERSON_LEFT_HAND;

        ((HeldItemRenderer)(Object)this).renderItem(
                player,
                stack,
                mode,
                arm == Arm.LEFT,
                matrices,
                vertexConsumers,
                light
        );

        matrices.pop();
    }
}
