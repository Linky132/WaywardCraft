package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import linky132.waywardcraft.common.entity.GhostEntity;
import linky132.waywardcraft.common.registries.ModBlocks;
import linky132.waywardcraft.common.registries.ModEntities;
import net.minecraft.world.entity.MobCategory;
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
    @SubscribeEvent
    public static void onLivingSpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof GhostEntity) {
            if (!event.getEntity().level.isClientSide) {
                event.getEntity().level.setBlock(event.getEntity().blockPosition(), ModBlocks.SKELETON.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().north(), ModBlocks.SKELETON.get().defaultBlockState().setValue(SkeletonBlock.HALF, DoubleBlockHalf.UPPER), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().above(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().above().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().below(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().below().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().east(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().east().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().west(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().west().north(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().south(), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
                event.getEntity().level.setBlock(event.getEntity().blockPosition().north(2), ModBlocks.GRAVEYARD_DIRT.get().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            }
        }
    }

    @SubscribeEvent
    public static void addSpawn(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.GHOST.get(), 100, 1, 5));
        }
    }
}