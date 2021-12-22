package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.util.ConfiguredFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * This class registers all PlacedFeatures.
 * All PlacedFeatures are actually registered in the main mod class ({@link WaywardCraft}).
 */
public class PlacedFeaturesRegistry {
        public static final PlacedFeature SALT_ROCK = PlacementUtils.register("salt_rock", ConfiguredFeatures.SALT_ROCK.placed(List.of(CountPlacement.of((30)), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-56), VerticalAnchor.absolute(100)), BiomeFilter.biome())));
}
