package com.addie.datagen;

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

            // Items
            provider.addTranslation(SmoredItems.MARSHMALLOW,"MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_SLIGHTLY_ROASTED,"Slightly Roasted MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED,"Roasted MarshMallow");
            provider.addTranslation(SmoredItems.MARSHMALLOW_BURNT,"Burnt MarshMallow");

            provider.addTranslation(SmoredItems.MARSHMALLOW_ON_A_STICK,"MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_SLIGHTLY_ROASTED_ON_A_STICK,"Slightly Roasted MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK,"Roasted MarshMallow On A Stick");
            provider.addTranslation(SmoredItems.MARSHMALLOW_BURNT_ON_A_STICK,"Burnt MarshMallow On A Stick");


            return provider;
        })));
    }
}
