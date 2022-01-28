package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import linky132.waywardcraft.common.blockentities.SkeletonBlockEntity;
import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.BlocksRegistry;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import linky132.waywardcraft.common.registries.PlacedFeaturesRegistry;
import linky132.waywardcraft.common.registries.StructuresRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusServerEvents {

    @SubscribeEvent
    public static void onLivingSpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Ghost) {
            if (!event.getWorld().isClientSide) {
                final BlockPos GRAVE_POSITION = event.getEntity().blockPosition().below(12);

                event.getEntity().level.setBlock(GRAVE_POSITION, BlocksRegistry.SKELETON.get().defaultBlockState(), Block.UPDATE_ALL);
                event.getEntity().level.setBlock(GRAVE_POSITION.north(), BlocksRegistry.SKELETON_TOP.get().defaultBlockState(), Block.UPDATE_ALL);
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

                ServerLevel serverLevel = (ServerLevel) event.getWorld();
                BlockPos blockPos = serverLevel.findNearestMapFeature(StructuresRegistry.CABIN.get(), event.getEntity().blockPosition(), 10000, false);
                System.out.println(blockPos);
                ItemStack mapItem = MapItem.create(event.getEntity().getLevel(), blockPos.getX(), blockPos.getZ(), (byte) 2, true, true);
                event.getWorld().getNearestPlayer(event.getEntity(), 10000).addItem(mapItem);
                MapItemSavedData.addTargetDecoration(mapItem, blockPos, "mansion", MapDecoration.Type.MANSION);
            }
        }
    }

    @SubscribeEvent
    public static void addSpawn(BiomeLoadingEvent event) {
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeaturesRegistry.SALT_ROCK);
    }
}