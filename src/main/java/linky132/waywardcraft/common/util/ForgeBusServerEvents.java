package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.ModBlocks;
import linky132.waywardcraft.common.registries.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * This class handles most events registered to the Forge event bus that run on the server
 */
@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusServerEvents {

    /**
     * Executes when an entity is spawned in the world.
     * @param event The event to subscribe to
     */
    @SubscribeEvent
    public static void onLivingSpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Ghost) {
            final BlockPos GRAVE_POSITION = event.getEntity().blockPosition().below(12);

            event.getEntity().level.setBlock(GRAVE_POSITION, ModBlocks.SKELETON.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.north(), ModBlocks.SKELETON.get().defaultBlockState().setValue(SkeletonBlock.HALF, DoubleBlockHalf.UPPER), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.above(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.above().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.below(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.below().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.east(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.east().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.west(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.west().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.south(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            event.getEntity().level.setBlock(GRAVE_POSITION.north(2), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);

            final SkeletonBlockEntity skeletonBlockEntity = (SkeletonBlockEntity) event.getEntity().level.getBlockEntity(GRAVE_POSITION);
            skeletonBlockEntity.setGhostUUID(event.getEntity().getUUID());
        }
    }

    /**
     * Adds spawn configurations for all custom mobs.
     * @param event The event to subscribe to
     */
    @SubscribeEvent
    public static void addSpawn(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getSpawns().getSpawner(ModEntities.RARE_CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.GHOST.get(), 1, 1, 1));
        }
    }
}