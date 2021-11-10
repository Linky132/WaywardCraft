package linky132.waywardcraft.client.models;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.entity.Ghost;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostModel extends AnimatedGeoModel<Ghost> {
    @Override
    public ResourceLocation getModelLocation(Ghost object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "geo/ghost.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Ghost object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "textures/entity/ghost/ghost.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Ghost object)
    {
        return new ResourceLocation(WaywardCraft.MOD_ID, "animations/ghost.animation.json");
    }
}
