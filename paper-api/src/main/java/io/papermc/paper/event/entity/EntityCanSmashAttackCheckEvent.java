package io.papermc.paper.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class EntityCanSmashAttackCheckEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    protected final LivingEntity entity;
    private final @Nullable ItemStack weapon;
    private final double fallDistance;
    private final double requiredFallDistance;
    private final boolean fallFlying;
    private final boolean originalResult;
    private final Reason reason;
    private Result result = Result.DEFAULT;

    @ApiStatus.Internal
    public EntityCanSmashAttackCheckEvent(
        final LivingEntity entity,
        final double fallDistance,
        final double requiredFallDistance,
        final boolean fallFlying,
        final boolean originalResult,
        final Reason reason
    ) {
        this(entity, null, fallDistance, requiredFallDistance, fallFlying, originalResult, reason);
    }

    @ApiStatus.Internal
    public EntityCanSmashAttackCheckEvent(
        final LivingEntity entity,
        final @Nullable ItemStack weapon,
        final double fallDistance,
        final double requiredFallDistance,
        final boolean fallFlying,
        final boolean originalResult,
        final Reason reason
    ) {
        super(entity);
        this.entity = entity;
        this.weapon = weapon;
        this.fallDistance = fallDistance;
        this.requiredFallDistance = requiredFallDistance;
        this.fallFlying = fallFlying;
        this.originalResult = originalResult;
        this.reason = reason;
    }

    public enum Reason {
        HURT_ENEMY,
        POST_HURT_ENEMY,
        ATTACK_DAMAGE_BONUS,
        DAMAGE_SOURCE
    }

    @Override
    public LivingEntity getEntity() {
        return this.entity;
    }

    @Nullable
    public ItemStack getWeapon() {
        return this.weapon != null ? this.weapon.clone() : null;
    }

    public double getFallDistance() {
        return this.fallDistance;
    }

    public double getRequiredFallDistance() {
        return this.requiredFallDistance;
    }

    public boolean isFallFlying() {
        return this.fallFlying;
    }

    public Reason getReason() {
        return this.reason;
    }

    /**
     * Yields the original result the server computed.
     *
     * @return {@code true} if this entity can perform a smash attack by vanilla's logic, {@code false} otherwise.
     */
    public boolean getOriginalResult() {
        return originalResult;
    }

    /**
     * Yields the effective result of this event.
     * The result may take one of three values:
     *
     * <ul>
     *     <li>{@link Result#ALLOW}: The check will succeed.</li>
     *     <li>{@link Result#DENY}: The check will fail.</li>
     *     <li>{@link Result#DEFAULT}: The check will succeed if {@link #getOriginalResult()} is {@code true} and fail otherwise.</li>
     * </ul>
     *
     * @return the result.
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * Configures a new result for this event.
     * The passes result may take one of three values:
     *
     * <ul>
     *     <li>{@link Result#ALLOW}: The check will succeed.</li>
     *     <li>{@link Result#DENY}: The check will fail.</li>
     *     <li>{@link Result#DEFAULT}: The check will succeed if {@link #getOriginalResult()} is {@code true} and fail otherwise.</li>
     * </ul>
     *
     * @param result the new result of the event.
     */
    public void setResult(final Result result) {
        this.result = result;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
