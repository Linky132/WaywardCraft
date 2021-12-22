package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.BlocksRegistry;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import linky132.waywardcraft.common.registries.PlacedFeaturesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * This class handles most server-side events that run on the Forge event bus
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

            event.getEntity().level.setBlock(GRAVE_POSITION, BlocksRegistry.SKELETON.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.north(), BlocksRegistry.SKELETON.get().defaultBlockState().setValue(SkeletonBlock.HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.above(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.above().north(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.below(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.below().north(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.east(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.east().north(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.west(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.west().north(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.south(), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);
            event.getEntity().level.setBlock(GRAVE_POSITION.north(2), BlocksRegistry.GRAVEYARD_DIRT.get().defaultBlockState(), Block.UPDATE_ALL);

            final SkeletonBlockEntity skeletonBlockEntity = (SkeletonBlockEntity) event.getEntity().level.getBlockEntity(GRAVE_POSITION);
            skeletonBlockEntity.setGhostUUID(event.getEntity().getUUID());
        }
    }

    /**
     * Executes when a biome is loaded.
     * @param event The event to subscribe to
     */
    @SubscribeEvent
    public static void addSpawn(BiomeLoadingEvent event) {
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeaturesRegistry.SALT_ROCK);
    }
}