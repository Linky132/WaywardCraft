package linky132.waywardcraft;

import linky132.waywardcraft.common.registries.BlockEntitiesRegistry;
import linky132.waywardcraft.common.registries.BlocksRegistry;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import linky132.waywardcraft.common.registries.ItemsRegistry;
import linky132.waywardcraft.common.util.WaywardCraftItemGroup;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup); // Add listener for the clientSetup method

        // Register everything in the DeferredRegisters.
        BlocksRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntitiesRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockEntitiesRegistry.BLOCKENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        GeckoLib.initialize(); // Initialize Geckolib
    }

    private void clientSetup(FMLClientSetupEvent event) {
//        .ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.SKELETON.get(), RenderType.cutout()); // Set custom render type for Skeleton
    }
}