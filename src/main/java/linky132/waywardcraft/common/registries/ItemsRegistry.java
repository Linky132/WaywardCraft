package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class registers most items to the ITEMS DeferredRegister and creates some items.
 * Spawn eggs are created and registered in the {@link EntitiesRegistry} class, but are registered in the ITEMS DeferredRegister.
 * In addition, BlockItems (formerly known as ItemBlocks) are registered in the {@link BlocksRegistry} class, but are registered in the ITEMS DeferredRegister.
 * Everything in the ITEMS DeferredRegister is actually registered in the main mod class ({@link WaywardCraft}).
 */
public class ItemsRegistry {
    // Create DeferredRegister
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WaywardCraft.MOD_ID);

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register Silver Ingot
}