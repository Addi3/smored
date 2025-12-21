package com.addie.datagen.providers;

import com.addie.Smored;
import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class SmoredAchievementProvider extends FabricAdvancementProvider {
    public SmoredAchievementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(SmoredItems.MARSHMALLOW_ON_A_STICK,
                        Text.translatable("achievement.smored.title.root"),
                        Text.translatable("achievement.smored.description.root"),
                        new Identifier("smored","textures/overlay/background.png"),
                        AdvancementFrame.GOAL, true, true, false)
                .criterion("root", InventoryChangedCriterion.Conditions.items(SmoredItems.MARSHMALLOW))
                .build(consumer, Smored.MOD_ID + "/root");

        Advancement burntMarshmallow = Advancement.Builder.create().parent(root)
                .display(SmoredItems.MARSHMALLOW_BURNT,
                        Text.translatable("achievement.smored.title.eat_burnt_marshmallow"),
                        Text.translatable("achievement.smored.description.eat_burnt_marshmallow"),
                        null, AdvancementFrame.CHALLENGE, true, true, true)
                .criterion("eat_burnt_marshmallow", ConsumeItemCriterion.Conditions.item(SmoredItems.MARSHMALLOW_BURNT))
                .build(consumer, Smored.MOD_ID + "/eat_burnt_marshmallow");

        Advancement burntSmore = Advancement.Builder.create().parent(burntMarshmallow)
                .display(SmoredItems.SMORE_BURNT_MARSHMALLOW,
                        Text.translatable("achievement.smored.title.eat_burnt_smore"),
                        Text.translatable("achievement.smored.description.eat_burnt_smore"),
                        null, AdvancementFrame.CHALLENGE, true, true, true)
                .criterion("eat_burnt_smore", ConsumeItemCriterion.Conditions.item(SmoredItems.SMORE_BURNT_MARSHMALLOW))
                .build(consumer, Smored.MOD_ID + "/eat_burnt_smore");

        Advancement jarBlock = Advancement.Builder.create().parent(root)
                .display(SmoredBlocks.MARSHMALLOW_JAR,
                        Text.translatable("achievement.smored.title.obtain_jar_block"),
                        Text.translatable("achievement.smored.description.obtain_jar_block"),
                        null, AdvancementFrame.TASK, true, true, true)
                .criterion("obtain_jar_block", InventoryChangedCriterion.Conditions.items(SmoredBlocks.MARSHMALLOW_JAR))
                .build(consumer, Smored.MOD_ID + "/obtain_jar_block");

        Advancement burntMarshmallowStick = Advancement.Builder.create().parent(root)
                .display(SmoredItems.MARSHMALLOW_SCORCHED_ON_A_STICK,
                        Text.translatable("achievement.smored.title.obtain_scorched_marshmallow_stick"),
                        Text.translatable("achievement.smored.description.obtain_scorched_marshmallow_stick"),
                        null, AdvancementFrame.TASK, true, true, true)
                .criterion("obtain_scorched_marshmallow_stick", InventoryChangedCriterion.Conditions.items(SmoredItems.MARSHMALLOW_SCORCHED_ON_A_STICK))
                .build(consumer, Smored.MOD_ID + "/obtain_scorched_marshmallow_stick");
    }
}
