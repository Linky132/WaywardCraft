package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.registries.StructuresRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class ConfiguredStructures {
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_CABIN = StructuresRegistry.CABIN.get().configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(WaywardCraft.MOD_ID, "configured_cabin"), CONFIGURED_CABIN);
    }
}
