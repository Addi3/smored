package com.addie.mixin.client;

import com.addie.core.item.MarshmallowOnAStickItem;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class MixinBipedEntityModel<T extends LivingEntity> {

    @Shadow public ModelPart leftArm;
    @Shadow public ModelPart rightArm;

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    private void onSetAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {

        ItemStack main = entity.getMainHandStack();
        ItemStack off = entity.getOffHandStack();

        if (isRoasting(main)) {
            if (leftArm != null) {
                leftArm.pitch = 0f;
                leftArm.yaw = 0f;
                leftArm.roll = 0f;
            }

            if (rightArm != null) {
                rightArm.pitch = 0f;
                rightArm.yaw = 0f;
                rightArm.roll = 0f;
            }
        }
    }

    private boolean isRoasting(ItemStack stack) {
        if (stack.getItem() instanceof MarshmallowOnAStickItem) {
            if (stack.hasNbt()) {
                return stack.getNbt().getBoolean("Roasting");
            }
        }
        return false;
    }
}
