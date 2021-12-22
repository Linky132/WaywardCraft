package linky132.waywardcraft.common.block;

import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
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

import javax.annotation.Nullable;

public class SkeletonBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SkeletonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, WATERLOGGED);
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
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (!level.isClientSide) {
            level.setBlock(blockPos.relative(blockState.getValue(FACING)), blockState.setValue(HALF, DoubleBlockHalf.UPPER), UPDATE_ALL);
        }
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
    }

    @Override
    public void onRemove(BlockState oldBlockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (!level.isClientSide) {
            if (oldBlockState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                level.destroyBlock(blockPos.relative(oldBlockState.getValue(FACING)), false);
            } else if (oldBlockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
                level.destroyBlock(blockPos.relative(oldBlockState.getValue(FACING).getOpposite()), false);
            }
            final SkeletonBlockEntity skeletonBlockEntity = (SkeletonBlockEntity) level.getBlockEntity(blockPos);
            if (skeletonBlockEntity.getGhostUUID() != null) {
                ServerLevel serverLevel = (ServerLevel) level;
                serverLevel.getEntity(skeletonBlockEntity.getGhostUUID()).kill();
            }
        }
        super.onRemove(oldBlockState, level, blockPos, newBlockState, isMoving);
    }


//    @Override
//    @Nullable
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
//        if (!p_153212_.isClientSide) {
//            return createTickerHelper(p_153214_, BlockEntitiesRegistry.SKELETON_BLOCKENTITY.get(), SkeletonBlock::printTickTock);
//        }
//        else {
//            return null;
//        }
//    }
//
//    public static void printTickTock(Level level, BlockPos blockPos, BlockState blockState, SkeletonBlockEntity skeletonBlockEntity) {
//        System.out.println("tick tock");
//    }
}