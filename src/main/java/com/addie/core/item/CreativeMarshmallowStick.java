package com.addie.core.item;

import com.addie.core.SmoredItems;
import com.lib.core.items.DifferingHandModelItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class CreativeMarshmallowStick extends Item implements DifferingHandModelItem {
    public CreativeMarshmallowStick(Item.Settings settings) {
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            if (!world.isClient) {
                user.getInventory().insertStack(
                        new ItemStack(SmoredItems.CREATIVE_MARSHMALLOW)
                );
                user.getInventory().insertStack(
                        new ItemStack(Items.STICK)
                );
                stack.decrement(1);
            }

            return TypedActionResult.success(stack, world.isClient);
        }

        return TypedActionResult.pass(stack);
    }

}


