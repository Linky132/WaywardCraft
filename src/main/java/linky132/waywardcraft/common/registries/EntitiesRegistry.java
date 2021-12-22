package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.Ghost;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class registers most entities to the ENTITIES DeferredRegister.
 * Spawn eggs are also created and registered here, as a workaround to the problem that items are normally registered before entities, causing a crash.
 * However, spawn eggs are still registered in the ITEMS DeferredRegister (the same DeferredRegister that all other items are registered in).
 * Everything in the ENTITIES DeferredRegister is actually registered in the main mod class ({@link WaywardCraft}).
 */
public class EntitiesRegistry {
    // Create custom mob category
    public static final MobCategory RARE_CREATURE = MobCategory.create("rare_creature", "rare_creature", 1, false, false, 128);

    // Create DeferredRegister
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WaywardCraft.MOD_ID);

    public static final RegistryObject<EntityType<Ghost>> GHOST = ENTITIES.register("ghost", () -> EntityType.Builder.of(Ghost::new, RARE_CREATURE).sized(0.6f, 1.95f).build(new ResourceLocation(WaywardCraft.MOD_ID, "ghost").toString())); // Register Ghost
}