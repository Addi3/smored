package com.addie.core.item;

import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class MarshmallowOnAStickItem extends Item {

    private static final int TICKS_PER_STAGE = 40;
    private static final int MAX_STAGE = 3;

    private static final TagKey<net.minecraft.block.Block> ROASTING_TAG =
            TagKey.of(RegistryKeys.BLOCK, new Identifier("smored", "roasting_spot"));

    public MarshmallowOnAStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);


        if (user.isSneaking()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            int stage = nbt.getInt("CookLevel");


            ItemStack marshmallowItem = switch (stage) {
                case 0 -> new ItemStack(SmoredItems.MARSHMALLOW);
                case 1 -> new ItemStack(SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED);
                case 2 -> new ItemStack(SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED);
                case 3 -> new ItemStack(SmoredItems.MARSHMALLOW_BURNT);
                default -> new ItemStack(SmoredItems.MARSHMALLOW);
            };

            if (!world.isClient) {
                user.getInventory().insertStack(marshmallowItem);
                user.getInventory().insertStack(new ItemStack(net.minecraft.item.Items.STICK));
                stack.decrement(1);
            }

            return TypedActionResult.success(stack);
        }


        if (!isNearRoastingSpot(world, user)) {
            return TypedActionResult.pass(stack);
        }

        stack.getOrCreateNbt().putBoolean("Roasting", true);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.getBoolean("Roasting")) return;


        if (!isNearRoastingSpot(world, player)) {
            nbt.putBoolean("Roasting", false);
            player.clearActiveItem();
            return;
        }

        int cookStage = nbt.getInt("CookLevel");
        int progress = nbt.getInt("CookProgress") + 1;


        boolean isNearCopperCampfire = isNearCopperCampfire(world, player);


        if (isNearCopperCampfire) {
            progress += 2; 
        }

        if (progress >= TICKS_PER_STAGE && cookStage < MAX_STAGE) {
            cookStage++;
            progress = 0;

            Item nextStageItem = switch (cookStage) {
                case 1 -> SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED_ON_A_STICK;
                case 2 -> SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED_ON_A_STICK;
                case 3 -> SmoredItems.MARSHMALLOW_BURNT_ON_A_STICK;
                default -> this;
            };

            ItemStack newStack = new ItemStack(nextStageItem, stack.getCount());
            NbtCompound newNbt = newStack.getOrCreateNbt();
            newNbt.putInt("CookLevel", cookStage);
            newNbt.putInt("CookProgress", progress);
            newNbt.putBoolean("Roasting", true);

            player.setStackInHand(player.getActiveHand(), newStack);
            return;
        }

        nbt.putInt("CookProgress", progress);
        nbt.putInt("CookLevel", cookStage);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean("Roasting") ? UseAction.CROSSBOW : UseAction.NONE;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.clearActiveItem();
        }
        return stack;
    }

    private boolean isNearRoastingSpot(World world, PlayerEntity player) {
        double maxDistance = 2.5;
        Vec3d eyePos = player.getEyePos();
        Vec3d lookVec = player.getRotationVec(1.0f);

        BlockPos base = player.getBlockPos();
        int radius = 2;
        for (BlockPos pos : BlockPos.iterateOutwards(base, radius, radius, radius)) {
            BlockState state = world.getBlockState(pos);
            if (!state.isIn(ROASTING_TAG)) continue;
            if (state.contains(Properties.LIT) && !state.get(Properties.LIT)) continue;

            Vec3d blockCenter = Vec3d.ofCenter(pos);
            if (eyePos.distanceTo(blockCenter) > maxDistance) continue;

            Vec3d toBlock = blockCenter.subtract(eyePos).normalize();
            double dot = lookVec.dotProduct(toBlock);
            if (dot > 0.95) return true;
        }
        return false;
    }


    private boolean isNearCopperCampfire(World world, PlayerEntity player) {
        BlockPos base = player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(base, 2, 2, 2)) {
            BlockState state = world.getBlockState(pos);
            if (state.isOf(SmoredBlocks.COPPER_CAMPFIRE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int stage = nbt.getInt("CookLevel");

        String stageKey = switch (stage) {
            case 0 -> "item.smored.marshmallow_on_a_stick.raw";
            case 1 -> "item.smored.marshmallow_on_a_stick.lightly_roasted";
            case 2 -> "item.smored.marshmallow_on_a_stick.perfectly_roasted";
            case 3 -> "item.smored.marshmallow_on_a_stick.burnt";
            default -> "item.smored.marshmallow_on_a_stick.unknown";
        };

        int color = switch (stage) {
            case 0 -> 0xc9a89d;
            case 1 -> 0xFFD27F;
            case 2 -> 0xFFAA00;
            case 3 -> 0x550000;
            default -> 0x7b1019;
        };

        tooltip.add(Text.translatable(stageKey).styled(s -> s.withColor(TextColor.fromRgb(color))));

        tooltip.add(Text.translatable("item.smored.marshmallow_on_a_stick.tooltip"));
        tooltip.add(Text.translatable("item.smored.marshmallow_on_a_stick.tooltipalt"));
    }

}
