package linky132.waywardcraft.common.blockentities;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class SkeletonBlockEntity extends BlockEntity {
    private UUID ghostUUID;

    public SkeletonBlockEntity(BlockPos position, BlockState state) {
        super(BlockEntitiesRegistry.SKELETON_BLOCKENTITY.get(), position, state);
    }

    public void setGhostUUID(UUID ghostUUID) {
        if (!this.getLevel().isClientSide) {
            ServerLevel serverLevel = (ServerLevel) this.getLevel();
            if (serverLevel.getEntity(ghostUUID) instanceof Ghost) {
                this.ghostUUID = ghostUUID;
                this.setChanged();
            } else {
                WaywardCraft.LOGGER.warn("This block is connected to a mob that is not a ghost. If this block is destroyed, the mob that this block is connected to will die instantly.");
            }
        }
    }

    public UUID getGhostUUID() {
        return ghostUUID;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (ghostUUID != null) {
            pTag.putUUID("UUID", ghostUUID);
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.ghostUUID = pTag.getUUID("UUID");
    }


}