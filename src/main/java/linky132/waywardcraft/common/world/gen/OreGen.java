package linky132.waywardcraft.common.world.gen;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.registries.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

/**
 * This class is used to generate blocks underground like ores or salt.
 * The class is annotated with the @Mod.EventBusSubscriber annotation, so that the loadBiomes event handler is registered to the Forge event bus.
 */
@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OreGen
{
    public static final ArrayList<ConfiguredFeature<?,?>> ORE_GENERATORS = new ArrayList<>();

    /**
     * This method actually generates the blocks and is called in the main mod class ({@link WaywardCraft})
     */
    public static void register()
    {
        registerOreGenerator(ModBlocks.BLOCK_OF_SALT.get().defaultBlockState(),14,200,70,"block_of_salt"); // Register the Salt Block ore generator
        registerOreGenerator(ModBlocks.SILVER_ORE.get().defaultBlockState(),6,40,20,"silver_ore"); // Register the Silver Ore ore generator
    }

    /**
     * This method creates a new ConfiguredFeature.
     * The method is called in the register method above.
     * @param blockState the blockstate that will be generated
     * @param veinSize the size of the vein. Needs more research
     * @param maxHeight the max height that the block will spawn at
     * @param spread how many blocks will generate in a chunk
     * @param name the name of the ConfiguredFeature
     */
    public static void registerOreGenerator(BlockState blockState, int veinSize, int maxHeight, int spread, String name)
    {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        ConfiguredFeature<?,?> configuredFeature = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE,blockState,veinSize)).rangeUniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(79)).squared().count(spread);
        ORE_GENERATORS.add(configuredFeature);
        Registry.register(registry, WaywardCraft.MOD_ID + name,configuredFeature);
    }

    /**
     * This event handler method generates ores when a new biome is created.
     * It is registered to the Forge event bus by the use of the @Mod.EventBusSubscriber annotation.
     * @param event the event that the method should listen to
     */
    @SubscribeEvent
    public static void loadBiomes(BiomeLoadingEvent event)
    {
        for(ConfiguredFeature<?,?> feature : ORE_GENERATORS)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,feature);
        }
    }
}