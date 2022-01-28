package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.registries.BlocksRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WaywardCraftCreativeTab extends CreativeModeTab {
    public WaywardCraftCreativeTab() {
        super(WaywardCraft.MOD_ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(BlocksRegistry.GRAVEYARD_DIRT.get());
    }
}