package com.addie.core;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class SmoredFoodComponenets {


    public static final FoodComponent MARSHMALLOW = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(0.5f)
            .snack()
            .alwaysEdible()
            .build();

    public static final FoodComponent MARSHMALLOW_COOKED = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0), 1.0f)
            .snack()
            .alwaysEdible()
            .build();

    public static final FoodComponent MARSHMALLOW_BURNT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(-0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 150, 0), 1.0f)
            .snack()
            .alwaysEdible()
            .build();

}

