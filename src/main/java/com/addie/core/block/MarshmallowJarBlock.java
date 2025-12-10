package com.addie.core.block;

import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MarshmallowJarBlock extends Block {

    public MarshmallowJarBlock(Settings settings) {
        super(settings);
    }

    protected static final VoxelShape SHAPE= Block.createCuboidShape(
            4, 0, 4, 12, 9, 12
    );

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              net.minecraft.entity.player.PlayerEntity player, Hand hand,
                              BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (isMarshmallowItem(item) && itemStack.getCount() > 0) {
            if (!world.isClient) {
                Block newBlock = getBlockForMarshmallow(item);

                world.setBlockState(pos, newBlock.getDefaultState());

                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_PLACE, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }


    private boolean isMarshmallowItem(Item item) {
        return item == SmoredItems.MARSHMALLOW ||
                item == SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED ||
                item == SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED ||
                item == SmoredItems.MARSHMALLOW_BURNT;
    }

    private Block getBlockForMarshmallow(Item item) {
        if (item == SmoredItems.MARSHMALLOW) {
            return SmoredBlocks.MARSHMALLOW_JAR_RAW;
        } else if (item == SmoredItems.MARSHMALLOW_LIGHTLY_ROASTED) {
            return SmoredBlocks.MARSHMALLOW_JAR_LIGHTLY_ROASTED;
        } else if (item == SmoredItems.MARSHMALLOW_PERFECTLY_ROASTED) {
            return SmoredBlocks.MARSHMALLOW_JAR_PERFECTLY_ROASTED;
        } else if (item == SmoredItems.MARSHMALLOW_BURNT) {
            return SmoredBlocks.MARSHMALLOW_JAR_BURNT;
        }

        return Blocks.AIR;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        if (!player.getAbilities().creativeMode) {
            this.dropStack(world, pos, new ItemStack(SmoredBlocks.MARSHMALLOW_JAR));

        }

        super.onBreak(world, pos, state, player);
    }
}
