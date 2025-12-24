package com.addie.core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.world.World;

import java.util.List;

public class CreativeSmore extends Item{
    public CreativeSmore(Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {


        tooltip.add(Text.translatable("item.smored.marshmallow_on_a_stick.creative")
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xb38ef3))));

    }
}
