package linky132.waywardcraft;

import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.ModBlockEntities;
import linky132.waywardcraft.common.registries.ModBlocks;
import linky132.waywardcraft.common.registries.ModEntities;
import linky132.waywardcraft.common.registries.ModItems;
import linky132.waywardcraft.common.util.WaywardCraftItemGroup;
import linky132.waywardcraft.common.world.gen.OreGen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

/**
 * This class is the main mod class/the entry class.
 * As such, it is annotated with the @Mod annotation.
 */
@Mod(WaywardCraft.MOD_ID)
public class WaywardCraft
{
    public static final String MOD_ID = "waywardcraft"; // Create a variable for the mod id
    public static final CreativeModeTab WAYWARDCRAFT_ITEMGROUP = new WaywardCraftItemGroup(); // Create a new instance of the custom ItemGroup.

    public WaywardCraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup); // Add listener for the commonSetup method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup); // Add listener for the clientSetup method

        // Actually register everything in the BLOCKS, ITEMS, and ENTITIES DeferredRegisters.
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCKENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        GeckoLib.initialize(); // Initialize Geckolib
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::afterCommonSetup); // Enqueue non-thread-safe code to run in the afterCommonSetup method
        SpawnPlacements.register(ModEntities.GHOST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Ghost::checkGhostSpawnRules);
    }

    private void afterCommonSetup() {
        OreGen.register(); // Register ore generation from the OreGen class
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SKELETON.get(), RenderType.cutout()); // Set custom render type for Skeleton
    }
}