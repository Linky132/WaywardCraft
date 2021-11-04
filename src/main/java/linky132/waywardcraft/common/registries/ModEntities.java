package linky132.waywardcraft.common.registries;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.GhostEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class registers most entities to the ENTITIES DeferredRegister.
 * Spawn eggs are also created and registered here, as a workaround to the problem that items are normally registered before entities, causing a crash.
 * However, spawn eggs are still registered in the ITEMS DeferredRegister (the same DeferredRegister that all other items are registered in).
 * Everything in the ENTITIES DeferredRegister is actually registered in the main mod class ({@link WaywardCraft}).
 */
@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WaywardCraft.MOD_ID); // Create the DeferredRegister for entities

    public static final RegistryObject<EntityType<GhostEntity>> GHOST = ENTITIES.register("ghost", () -> EntityType.Builder.of(GhostEntity::new, MobCategory.MONSTER).sized(0.6f, 1.95f).build(new ResourceLocation(WaywardCraft.MOD_ID, "ghost").toString())); // Register Ghost
}