package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockEntities {
    // Create DeferredRegister
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, WaywardCraft.MOD_ID);

    // Create and/or register BlockEntities
    public static final RegistryObject<BlockEntityType<SkeletonBlockEntity>> SKELETON_BLOCKENTITY = BLOCKENTITIES.register("skeleton", () -> BlockEntityType.Builder.of(SkeletonBlockEntity::new, ModBlocks.SKELETON.get()).build(null)); // Register Skeleton BlockEntity
}
