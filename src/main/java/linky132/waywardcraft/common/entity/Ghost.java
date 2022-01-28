package linky132.waywardcraft.common.entity;

import linky132.waywardcraft.WaywardCraft;
import linky132.waywardcraft.common.registries.StructuresRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class Ghost extends Monster implements IAnimatable {
    private AnimationFactory animationFactory = new AnimationFactory(this);

    public Ghost(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    /**
     * Register the Ghost's goals, i.e. its behaviour/what it should do
     */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 20.0F)); // Look at the nearest player
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 2.2D, false)); // Attack the target, in this case, the nearest player
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false)); // Find the nearest player
    }

    /**
     * Sets the attributes for the Ghost.
     * Attributes are actually applied to the entity in the main mod class ({@link WaywardCraft}).
     *
     * @return the attributes for the Ghost
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!((animationSpeed > -0.10F) || !(animationSpeed < 0.10F))) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "animationController", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    @Override
    public void checkDespawn() {
        if (!this.level.isClientSide()) {
            if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
                this.discard();
            }
        }
    }
}