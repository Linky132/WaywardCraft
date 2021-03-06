package linky132.waywardcraft.common.util;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.client.renderers.GhostRenderer;
import linky132.waywardcraft.common.registries.EntitiesRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WaywardCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesRegistry.GHOST.get(), GhostRenderer::new);
    }
}