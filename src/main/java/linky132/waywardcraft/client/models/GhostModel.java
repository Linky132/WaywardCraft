package linky132.waywardcraft.client.models;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.GhostEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostModel extends AnimatedGeoModel<GhostEntity> {
    @Override
    public ResourceLocation getModelLocation(GhostEntity object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "geo/ghost.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhostEntity object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "textures/entity/ghost/ghost.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhostEntity object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "animations/ghost.animation.json");
    }
}
