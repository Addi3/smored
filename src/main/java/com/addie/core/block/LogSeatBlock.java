package com.addie.core.block;

import com.addie.core.SmoredEntityTypes;
import com.addie.core.entity.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.block.ShapeContext;

import java.util.List;

public class LogSeatBlock extends HorizontalFacingBlock {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    protected static final VoxelShape SHAPE_NS =
            Block.createCuboidShape(0, 0, 4, 16, 8, 12);

    protected static final VoxelShape SHAPE_EW =
            Block.createCuboidShape(4, 0, 0, 12, 8, 16);

    public LogSeatBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return (dir == Direction.NORTH || dir == Direction.SOUTH)
                ? SHAPE_NS
                : SHAPE_EW;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getCollisionShape(state, world, pos, context);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (player.hasVehicle()) {
            return ActionResult.SUCCESS;
        }

        List<SeatEntity> seats = world.getEntitiesByClass(
                SeatEntity.class,
                new Box(pos).expand(0.1),
                seat -> true
        );

        if (!seats.isEmpty()) {
            return ActionResult.SUCCESS;
        }

        float yaw = state.get(FACING).asRotation();

        SeatEntity seat = new SeatEntity(SmoredEntityTypes.SEAT, world);
        seat.refreshPositionAndAngles(
                pos.getX() + 0.5,
                pos.getY() - 0.2,
                pos.getZ() + 0.5,
                yaw,
                0
        );

        world.spawnEntity(seat);
        player.startRiding(seat, false);

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                BlockState newState, boolean moved) {

        if (state.getBlock() != newState.getBlock() && !world.isClient) {
            List<SeatEntity> seats = world.getEntitiesByClass(
                    SeatEntity.class,
                    new Box(pos).expand(0.1),
                    seat -> true
            );
            seats.forEach(SeatEntity::discard);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
