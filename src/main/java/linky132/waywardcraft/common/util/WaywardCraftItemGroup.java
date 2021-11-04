package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.registries.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * Custom ItemGroup for WaywardCraft
 */
public class WaywardCraftItemGroup extends CreativeModeTab {
    public WaywardCraftItemGroup() {
        super(WaywardCraft.MOD_ID);
    }

    /**
     * Creates an icon for the ItemGroup.
     * @return ItemStack to use as the icon
     */
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.GRAVEYARD_DIRT.get());
    }
}