package com.addie.core;

import com.addie.core.item.MarshmallowOnAStickItem;
import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class SmoredItems extends ItemContainer {

    @NoEnglish
    public static final Item MARSHMALLOW = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_SLIGHTLY_ROASTED = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED).maxCount(16));
    @NoEnglish
    public static final Item MARSHMALLOW_PERFECTLY_ROASTED = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_COOKED).maxCount(16));
    @NoEnglish
    public static final Item MARSHMALLOW_BURNT = new Item(new AItemSettings().group(SmoredItemGroups.MAIN).food(SmoredFoodComponenets.MARSHMALLOW_BURNT).maxCount(16));

    @NoEnglish
    public static final Item MARSHMALLOW_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().group(SmoredItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_SLIGHTLY_ROASTED_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

    @NoEnglish
    public static final Item MARSHMALLOW_BURNT_ON_A_STICK = new MarshmallowOnAStickItem(new AItemSettings().maxCount(1));

}