package com.addie.core.block;

import com.addie.core.SmoredBlocks;
import com.addie.core.SmoredItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.nbt.NbtCompound;

public class MarshmallowJarRawBlock extends Block {

    public static final IntProperty ITEM_COUNT = IntProperty.of("item_count", 1, 16);

    public MarshmallowJarRawBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ITEM_COUNT, 1));
    }

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 9, 12);

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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ITEM_COUNT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand,
                              BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        int currentCount = state.get(ITEM_COUNT);


        if (item == Items.STICK && currentCount > 0) {
            return handleStickInteraction(world, pos, state, player, currentCount);
        }


        if (itemStack.isEmpty() && currentCount > 0) {
            return handleEmptyHandInteraction(world, pos, state, player, currentCount);
        }


        if (item == SmoredItems.MARSHMALLOW && currentCount < 16) {
            return handleAddMarshmallowInteraction(world, pos, state, player, currentCount, itemStack);
        }

        return ActionResult.PASS;
    }

    private ActionResult handleStickInteraction(World world, BlockPos pos, BlockState state,
                                                PlayerEntity player, int currentCount) {
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_PLACE, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 1.3F);


        ItemStack marshmallowOnStick = getMarshmallowOnStick(currentCount);

        if (!player.getInventory().insertStack(marshmallowOnStick)) {
            player.giveItemStack(marshmallowOnStick);
        }

        int newCount = Math.max(currentCount - 1, 1);
        world.setBlockState(pos, state.with(ITEM_COUNT, newCount));

        if (newCount == 1) {
            if (!world.isClient) {
                world.setBlockState(pos, SmoredBlocks.MARSHMALLOW_JAR.getDefaultState());
            }
        }

        return ActionResult.SUCCESS;
    }

    private ItemStack getMarshmallowOnStick(int currentCount) {

        ItemStack marshmallowOnStick = new ItemStack(SmoredItems.MARSHMALLOW_ON_A_STICK);
        NbtCompound nbt = marshmallowOnStick.getOrCreateNbt();
        int cookLevel = getCookLevelForJar(currentCount);
        nbt.putInt("CookLevel", cookLevel);
        return marshmallowOnStick;
    }

    private int getCookLevelForJar(int currentCount) {

        if (currentCount == 1) return 0;
        if (currentCount == 2) return 1;
        if (currentCount == 3) return 2;
        return 3;
    }

    private ActionResult handleEmptyHandInteraction(World world, BlockPos pos, BlockState state,
                                                    PlayerEntity player, int currentCount) {
        if (!player.getInventory().insertStack(new ItemStack(SmoredItems.MARSHMALLOW))) {
            player.giveItemStack(new ItemStack(SmoredItems.MARSHMALLOW));
        }

        int newCount = Math.max(currentCount - 1, 1);
        world.setBlockState(pos, state.with(ITEM_COUNT, newCount));

        if (newCount == 1) {
            if (!world.isClient) {
                world.setBlockState(pos, SmoredBlocks.MARSHMALLOW_JAR.getDefaultState());
            }
        }

        return ActionResult.SUCCESS;
    }

    private ActionResult handleAddMarshmallowInteraction(World world, BlockPos pos, BlockState state,
                                                         PlayerEntity player, int currentCount,
                                                         ItemStack itemStack) {
        if (currentCount < 16) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            int newCount = currentCount + 1;
            world.setBlockState(pos, state.with(ITEM_COUNT, newCount));

            world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_PLACE, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (newCount == 16) {
                world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_HIT,
                        net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, 0.0F);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int itemCount = state.get(ITEM_COUNT);
        if (!player.getAbilities().creativeMode) {
            this.dropStack(world, pos, new ItemStack(SmoredBlocks.MARSHMALLOW_JAR));

            for (int i = 0; i < itemCount; i++) {
                this.dropStack(world, pos, new ItemStack(SmoredItems.MARSHMALLOW));
            }
        }

        super.onBreak(world, pos, state, player);
    }
}
