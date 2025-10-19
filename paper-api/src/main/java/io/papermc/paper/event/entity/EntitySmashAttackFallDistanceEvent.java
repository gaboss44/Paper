package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import javax.annotation.Nullable;

@NullMarked
public class EntitySmashAttackFallDistanceEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Nullable
    private final LivingEntity target;

    private final ItemStack weapon;
    private double fallDistance;
    private final double originalFallDistance;
    private final Reason reason;

    @ApiStatus.Internal
    public EntitySmashAttackFallDistanceEvent(
        final Entity attacker,
        final @Nullable LivingEntity target,
        final ItemStack weapon,
        final double fallDistance,
        final Reason reason
    ) {
        super(attacker);
        this.target = target;
        this.weapon = weapon;
        this.fallDistance = fallDistance;
        this.originalFallDistance = fallDistance;
        this.reason = reason;
    }

    public enum Reason {
        ATTACK_DAMAGE_BONUS,
        SOUND_EFFECT,
        KNOCKBACK_POWER,
        WIND_BURST_CHECK
    }

    /**
     * Yields the target of the smash attack.
     *
     * @return the target entity
     */
    @Nullable
    public LivingEntity getTarget() {
        return target;
    }

    /**
     * Yields a copy of the itemstack used in the smash attack.
     *
     * @return the itemstack
     */
    public ItemStack getWeapon() {
        return weapon.clone();
    }

    public double getFallDistance() {
        return this.fallDistance;
    }

    public void setFallDistance(double var1) {
        this.fallDistance = var1;
    }

    public double getOriginalFallDistance() {
        return this.originalFallDistance;
    }

    public Reason getReason() {
        return this.reason;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
