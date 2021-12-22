package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.client.renderers.GhostRenderer;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * This class handles most client-side events that run on the Mod event bus
 */
@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    /**
     * Registers the renderers for all custom entities.
     * @param event The event to subscribe to
     */
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesRegistry.GHOST.get(), GhostRenderer::new);
    }
}