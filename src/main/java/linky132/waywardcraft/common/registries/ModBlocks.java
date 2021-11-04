package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class registers most blocks to the BLOCKS DeferredRegister, and also creates some blocks.
 * BlockItems (formerly known as ItemBlocks) are also registered in this class, but are registered in the ITEMS DeferredRegister.
 * Everything in the BLOCKS DeferredRegister is actually registered in the main mod class ({@link WaywardCraft}).
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WaywardCraft.MOD_ID);

    // Register blocks
    public static final RegistryObject<Block> BLOCK_OF_SALT = BLOCKS.register("block_of_salt", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 1.5f).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Block of Salt
    public static final RegistryObject<Block> BLOCK_OF_SILVER = BLOCKS.register("block_of_silver", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6.0f, 5.0f).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Block of Silver
    public static final RegistryObject<Block> SKELETON = BLOCKS.register("skeleton", () -> new SkeletonBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).sound(SoundType.BONE_BLOCK).strength(-1.0f, -1.0f).noOcclusion())); // Register Villager Skeleton
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Silver Ore
    public static final RegistryObject<Block> GRAVEYARD_DIRT = BLOCKS.register("graveyard_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6.0f, 5.0f).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Graveyard Dirt

    // Register BlockItems
    public static final RegistryObject<Item> BLOCK_OF_SALT_ITEM = ModItems.ITEMS.register("block_of_salt", () -> new BlockItem(BLOCK_OF_SALT.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Block of Salt
    public static final RegistryObject<Item> BLOCK_OF_SILVER_ITEM = ModItems.ITEMS.register("block_of_silver", () -> new BlockItem(BLOCK_OF_SILVER.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Block of Silver
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ModItems.ITEMS.register("silver_ore", () -> new BlockItem(SILVER_ORE.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Silver Ore
    public static final RegistryObject<Item> GRAVEYARD_DIRT_ITEM = ModItems.ITEMS.register("graveyard_dirt", () -> new BlockItem(GRAVEYARD_DIRT.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Graveyard Dirt
    public static final RegistryObject<Item> SKELETON_ITEM = ModItems.ITEMS.register("skeleton", () -> new BlockItem(SKELETON.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Graveyard Dirt
}