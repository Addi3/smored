package com.addie.core;

import com.addie.Smored;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

public class SmoredItemGroups implements ItemGroupContainer {

    public static final AItemGroup MAIN = AItemGroup.builder(Smored.id("item_group"))
            .icon(() -> new ItemStack(SmoredItems.MARSHMALLOW))
            .build();
}
