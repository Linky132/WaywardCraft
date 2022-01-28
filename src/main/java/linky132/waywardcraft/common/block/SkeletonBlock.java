package linky132.waywardcraft.common.block;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import linky132.waywardcraft.common.registries.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;

public class SkeletonBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SkeletonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SkeletonBlockEntity(pPos, pState);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public void onRemove(BlockState oldBlockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (!level.isClientSide) {
            final SkeletonBlockEntity skeletonBlockEntity = (SkeletonBlockEntity) level.getBlockEntity(blockPos);
            if (skeletonBlockEntity.getGhostUUID() != null) {
                ServerLevel serverLevel = (ServerLevel) level;
                serverLevel.getEntity(skeletonBlockEntity.getGhostUUID()).kill();
            } else {
                WaywardCraft.LOGGER.warn("At the time of removal, this block was not connected to a ghost. This could mean that this block was placed in the world by cheats such as commands. Or this could mean that an error occurred and a ghost was not successfully attached to this block. If it was the latter case, the ghost that this block was supposed to be attached to is now COMPLETELY UNKILLABLE. The only way to kill the ghost would be via cheats.");
            }
        }
        super.onRemove(oldBlockState, level, blockPos, newBlockState, isMoving);
    }
}