package com.addie.core;

import com.addie.core.item.*;
import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class SmoredItems extends ItemContainer {

    @NoEnglish
    public static final Item MARSHMALLOW = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW).maxCount(16));

    @NoEnglish
    public static final Item CREATIVE_MARSHMALLOW = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.CREATIVE_MARSHMALLOW).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_LIGHTLY_ROASTED = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_PERFECTLY_ROASTED = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_BURNT = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_BURNT).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_SCORCHED = new MarshmallowScorchedItem(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_BURNT).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().group(SmoredItemGroups.MAIN).maxCount(1));

    // gained by throwing MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK into end void, it will then be at 10.2-block radius around the End dimension's 0, 0  //
    @NoEnglish
    public static final Item CREATIVE_MARSHMALLOW_ON_A_STICK = new CreativeMarshmallowStick(new AItemSettings().group(SmoredItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_LIGHTLY_ROASTED_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_BURNT_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_SCORCHED_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item CRACKER = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW));

    @NoEnglish
    public static final Item SMORE = new SmoreItem(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW));

    @NoEnglish
    public static final Item CREATIVE_SMORE = new CreativeSmore(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.CREATIVE_MARSHMALLOW));

    @NoEnglish
    public static final Item SMORE_LIGHTLY_ROASTED_MARSHMALLOW = new SmoreLightlyRoastedItem(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED));

    @NoEnglish
    public static final Item SMORE_PERFECTLY_ROASTED_MARSHMALLOW = new SmorePerfectlyRoastedItem(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED_REGEN));

    @NoEnglish
    public static final Item SMORE_BURNT_MARSHMALLOW = new SmoreBurntItem(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_BURNT));

}