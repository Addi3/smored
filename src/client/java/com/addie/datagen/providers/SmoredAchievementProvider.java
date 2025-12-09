package com.addie.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class SmoredAchievementProvider extends FabricAdvancementProvider {
    public SmoredAchievementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
//        Advancement root = Advancement.Builder.create()
//                .display(AITExtrasItems.MERCURY_DISC,
//                        Text.translatable("achievement.ait-extras.title.root"),
//                        Text.translatable("achievement.ait-extras.description.root"),
//                        new Identifier("textures/block/anvil.png"),
//                        AdvancementFrame.TASK, false, false, false)
//                .criterion("root", InventoryChangedCriterion.Conditions.items(AITExtrasItems.JELLY_BABIES))
//                .build(consumer, AITExtras.MOD_ID + "/root");
//
//        Advancement jellyBabies = Advancement.Builder.create().parent(root)
//                .display(AITExtrasItems.JELLY_BABIES,
//                        Text.translatable("achievement.ait-extras.title.obtain_jelly_babies"),
//                        Text.translatable("achievement.ait-extras.description.obtain_jelly_babies"),
//                        null, AdvancementFrame.GOAL, true, true, true)
//                .criterion("obtain_jelly_babies", InventoryChangedCriterion.Conditions.items(AITExtrasItems.JELLY_BABIES))
//                .build(consumer, AITExtras.MOD_ID + "/obtain_jelly_babies");


    }
}
