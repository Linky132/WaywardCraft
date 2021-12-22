package linky132.waywardcraft.common.blockentities;

import linky132.waywardcraft.common.registries.BlockEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class SkeletonBlockEntity extends BlockEntity {
    private UUID ghostUUID;

    public SkeletonBlockEntity(BlockPos position, BlockState state) {
        super(BlockEntitiesRegistry.SKELETON_BLOCKENTITY.get(), position, state);
        this.ghostUUID = null;
    }

    public void setGhostUUID(UUID ghostUUID) {
        this.ghostUUID = ghostUUID;
        this.setChanged();
    }

    public UUID getGhostUUID() {
        return ghostUUID;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putUUID("ghost", ghostUUID);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.ghostUUID = pTag.getUUID("ghost");
    }
}