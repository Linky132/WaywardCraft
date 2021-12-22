package linky132.waywardcraft.common.util;

import linky132.waywardcraft.common.registries.BlocksRegistry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> SALT_ROCK = FeatureUtils.register("salt_rock", Feature.ORE.configured(new OreConfiguration(OreFeatures.NATURAL_STONE, BlocksRegistry.SALT_ROCK.get().defaultBlockState(), 20)));
}