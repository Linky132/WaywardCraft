package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.block.SkeletonBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class registers most blocks to the BLOCKS DeferredRegister, and also creates some blocks.
 * BlockItems (formerly known as ItemBlocks) are also registered in this class, but are registered in the ITEMS DeferredRegister.
 * Everything in the BLOCKS DeferredRegister is actually registered in the main mod class ({@link WaywardCraft}).
 */
public class BlocksRegistry {
    // Create DeferredRegister
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WaywardCraft.MOD_ID);

    // Create and/or register block
    public static final RegistryObject<Block> SALT_ROCK = BLOCKS.register("salt_rock", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 1.5F).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Salt Rock
    public static final RegistryObject<Block> BLOCK_OF_SILVER = BLOCKS.register("block_of_silver", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6.0F, 5.0F).sound(SoundType.METAL).requiresCorrectToolForDrops())); // Create and register Block of Silver
    public static final RegistryObject<Block> SKELETON = BLOCKS.register("skeleton", () -> new SkeletonBlock(Block.Properties.of(Material.STONE).sound(SoundType.BONE_BLOCK).strength(-1.0F, -1.0f).noOcclusion())); // Register Skeleton
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops())); // Create and register Silver Ore
    public static final RegistryObject<Block> GRAVEYARD_DIRT = BLOCKS.register("graveyard_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL))); // Create and register Graveyard Dirt

    // Create and/or register BlockItems
    public static final RegistryObject<Item> SALT_ROCK_ITEM = ItemsRegistry.ITEMS.register("salt_rock", () -> new BlockItem(SALT_ROCK.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Salt Rock
    public static final RegistryObject<Item> BLOCK_OF_SILVER_ITEM = ItemsRegistry.ITEMS.register("block_of_silver", () -> new BlockItem(BLOCK_OF_SILVER.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Block of Silver
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ItemsRegistry.ITEMS.register("silver_ore", () -> new BlockItem(SILVER_ORE.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Silver Ore
    public static final RegistryObject<Item> GRAVEYARD_DIRT_ITEM = ItemsRegistry.ITEMS.register("graveyard_dirt", () -> new BlockItem(GRAVEYARD_DIRT.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Graveyard Dirt
    public static final RegistryObject<Item> SKELETON_ITEM = ItemsRegistry.ITEMS.register("skeleton", () -> new BlockItem(SKELETON.get(), new Item.Properties().tab(WaywardCraft.WAYWARDCRAFT_ITEMGROUP))); // Create and register ItemBlock for Graveyard Dirt
}