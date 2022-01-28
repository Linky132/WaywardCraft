package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.Ghost;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusServerEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntitiesRegistry.GHOST.get(), Ghost.createAttributes().build());
    }
}
