package com.addie.core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmoreLightlyRoastedItem extends Item {
    public SmoreLightlyRoastedItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);


        tooltip.add(Text.translatable("item.smored.marshmallow_on_a_stick.lightly_roasted")
                .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD27F))));

    }
}