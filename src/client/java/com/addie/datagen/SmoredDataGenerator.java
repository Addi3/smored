package com.addie.datagen;

import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItemGroups;
import com.addie.core.SmoredItems;
import com.addie.datagen.providers.SmoredAchievementProvider;
import com.addie.datagen.providers.SmoredRecipeProvider;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.security.Provider;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromItem;
import static net.minecraft.data.server.recipe.RecipeProvider.hasItem;

public class SmoredDataGenerator implements DataGeneratorEntrypoint {
	@Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        generateachivement(pack);
        genLang(pack);
        generateRecipes(pack);
	}

    private void generateachivement(FabricDataGenerator.Pack pack) {
        pack.addProvider(SmoredAchievementProvider::new);
    }

    public void generateRecipes(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            SmoredRecipeProvider provider = new SmoredRecipeProvider(output);

            // MISC
            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, SmoredBlocks.COPPER_CAMPFIRE, 1)
                    .group("marshmallow")
                    .pattern(" S ")
                    .pattern("SCS")
                    .pattern("LLL")
                    .input('S',Items.STICK)
                    .input('C',Items.COPPER_INGOT)
                    .input('L',ItemTags.LOGS)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, SmoredBlocks.MARSHMALLOW_JAR, 2)
                    .group("marshmallow")
                    .pattern(" W ")
                    .pattern("G G")
                    .pattern("GGG")
                    .input('G',Blocks.GLASS)
                    .input('W',ItemTags.WOOL_CARPETS)
                    .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS)));

            // MARSHMALLOWS
            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.MARSHMALLOW, 4)
                    .group("marshmallow")
                    .pattern("WSW")
                    .input('S',Items.SUGAR)
                    .input('W',Items.WHEAT)
                    .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                    .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.MARSHMALLOW_ON_A_STICK, 1)
                    .group("marshmallow")
                    .pattern("M")
                    .pattern("S")
                    .input('S',Items.STICK)
                    .input('M',SmoredItems.MARSHMALLOW)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW), conditionsFromItem(SmoredItems.MARSHMALLOW)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED, 1)
                    .group("marshmallow")
                    .pattern("M")
                    .pattern("S")
                    .input('S',Items.STICK)
                    .input('M',SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED), conditionsFromItem(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK, 1)
                    .group("marshmallow")
                    .pattern("M")
                    .pattern("S")
                    .input('S',Items.STICK)
                    .input('M',SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED), conditionsFromItem(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.MARSHMALLOW_BURNT_ON_A_STICK, 1)
                    .group("marshmallow")
                    .pattern("M")
                    .pattern("S")
                    .input('S',Items.STICK)
                    .input('M',SmoredItems.MARSHMALLOW_BURNT)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_BURNT), conditionsFromItem(SmoredItems.MARSHMALLOW_BURNT)));

            // SMORES
            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.CRACKER, 2)
                    .group("smore")
                    .pattern("WE")
                    .input('E',Items.EGG)
                    .input('W',Items.WHEAT)
                    .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                    .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.SMORE, 4)
                    .group("smore")
                    .pattern("CMK")
                    .pattern("  C")
                    .input('C',SmoredItems.CRACKER)
                    .input('M',SmoredItems.MARSHMALLOW)
                    .input('K',Items.COCOA_BEANS)
                    .criterion(hasItem(SmoredItems.CRACKER), conditionsFromItem(SmoredItems.CRACKER))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW), conditionsFromItem(SmoredItems.MARSHMALLOW))
                    .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.SMORE_LIGHTLY_ROASTED_MARSHMALLOW, 4)
                    .group("smore")
                    .pattern("CMK")
                    .pattern("  C")
                    .input('C',SmoredItems.CRACKER)
                    .input('M',SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED)
                    .input('K',Items.COCOA_BEANS)
                    .criterion(hasItem(SmoredItems.CRACKER), conditionsFromItem(SmoredItems.CRACKER))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED), conditionsFromItem(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED))
                    .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.SMORE_PERFECTLY_ROASTED_MARSHMALLOW, 4)
                    .group("smore")
                    .pattern("CMK")
                    .pattern("  C")
                    .input('C',SmoredItems.CRACKER)
                    .input('M',SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED)
                    .input('K',Items.COCOA_BEANS)
                    .criterion(hasItem(SmoredItems.CRACKER), conditionsFromItem(SmoredItems.CRACKER))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED), conditionsFromItem(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED))
                    .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS)));


            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, SmoredItems.SMORE_BURNT_MARSHMALLOW, 4)
                    .group("smore")
                    .pattern("CMK")
                    .pattern("  C")
                    .input('C',SmoredItems.CRACKER)
                    .input('M',SmoredItems.MARSHMALLOW_BURNT)
                    .input('K',Items.COCOA_BEANS)
                    .criterion(hasItem(SmoredItems.CRACKER), conditionsFromItem(SmoredItems.CRACKER))
                    .criterion(hasItem(SmoredItems.MARSHMALLOW_BURNT), conditionsFromItem(SmoredItems.MARSHMALLOW_BURNT))
                    .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS)));

            return provider;

        })));
    }

    private void genLang(FabricDataGenerator.Pack pack) {
        genEnglish(pack);
    }

    private void genEnglish(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

            // Misc
            provider.addTranslation(SmoredItemGroups.MAIN,"Smore'd");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.raw","Uncooked Marshmallow");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.lightly_roasted","Lightly Roasted Marshmallow");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.perfectly_roasted","Perfectly Roasted Marshmallow");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.burnt","Burnt Marshmallow");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.unknown","umm... how did you get this state?");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.tooltip","Shift + Right-Click to take marshmallow off stick!");
            provider.addTranslation("item.smored.marshmallow_on_a_stick.tooltipalt","Right-Click over campfire to cook!");

            // Items
            provider.addTranslation(SmoredItems.MARSHMALLOW,"MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED,"Lightly Roasted MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED,"Perfectly Roasted MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_BURNT,"Burnt MarshMallow");
            provider.addTranslation(SmoredItems.CRACKER,"Cracker");
            provider.addTranslation(SmoredItems.SMORE,"Smore");
            provider.addTranslation(SmoredItems.SMORE_BURNT_MARSHMALLOW,"Smore");
            provider.addTranslation(SmoredItems.SMORE_PERFECTLY_ROASTED_MARSHMALLOW,"Smore");
            provider.addTranslation(SmoredItems.SMORE_LIGHTLY_ROASTED_MARSHMALLOW,"Smore");

            provider.addTranslation(SmoredItems.MARSHMALLOW_ON_A_STICK,"MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED_ON_A_STICK,"MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK,"MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_BURNT_ON_A_STICK,"MarshMallow On A Stick");

            // Blocks
            provider.addTranslation(SmoredBlocks.MARSHMALLOW_JAR,"Jar");
            provider.addTranslation(SmoredBlocks.MARSHMALLOW_JAR_RAW,"Jar");
            provider.addTranslation(SmoredBlocks.MARSHMALLOW_JAR_BURNT,"Jar");
            provider.addTranslation(SmoredBlocks.MARSHMALLOW_JAR_PERFECTLY_ROASTED,"Jar");
            provider.addTranslation(SmoredBlocks.MARSHMALLOW_JAR_LIGHTLY_ROASTED,"Jar");
            provider.addTranslation(SmoredBlocks.COPPER_CAMPFIRE,"Copper Campfire");

            // Achivements

                //Root
            provider.addTranslation("achievement.smored.title.root","Sweet Treat!");
            provider.addTranslation("achievement.smored.description.root","Craft a marshmallow");

                //Burnt Marshmallow
            provider.addTranslation("achievement.smored.title.eat_burnt_marshmallow","Mmmm, Carcinogens");
            provider.addTranslation("achievement.smored.description.eat_burnt_marshmallow","Eat a burnt marshmallow");

                //Burnt Smore
            provider.addTranslation("achievement.smored.title.eat_burnt_smore","Mmm, Cruchy");
            provider.addTranslation("achievement.smored.description.eat_burnt_smore","Eat a burnt smore");

                //Obtain Jar
            provider.addTranslation("achievement.smored.title.obtain_jar_block","Storage 101");
            provider.addTranslation("achievement.smored.description.obtain_jar_block","Craft a jar to contain marshmallows");

            return provider;
        })));
    }
}
