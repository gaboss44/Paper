package io.papermc.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;

/**
 * Called when the game determines whether a player's attack will be critical.
 * Plugins can override the decision or adjust the resulting damage multiplier.
 */
@NullMarked
public class PlayerAttackEntityCriticalCheckEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final Entity target;

    private final ItemStack weapon;

    private final float baseDamage;

    private final float enchantedDamage;

    private final float strengthScale;

    private boolean critical;

    private final boolean originallyCritical;

    private float criticalMultiplier;

    private final float originalCriticalMultiplier;

    @ApiStatus.Internal
    public PlayerAttackEntityCriticalCheckEvent(
        @NotNull Player player,
        @NotNull Entity target,
        @NotNull ItemStack weapon,
        float baseDamage,
        float enchantedDamage,
        float strengthScale,
        boolean critical,
        float criticalMultiplier
    ) {
        super(player);
        this.target = target;
        this.weapon = weapon;
        this.baseDamage = baseDamage;
        this.enchantedDamage = enchantedDamage;
        this.strengthScale = strengthScale;
        this.critical = critical;
        this.originallyCritical = critical;
        this.criticalMultiplier = criticalMultiplier;
        this.originalCriticalMultiplier = criticalMultiplier;
    }

    /**
     * @return The target entity being attacked.
     */
    public Entity getTarget() {
        return target;
    }

    /**
     * @return The weapon used in the attack.
     */
    public ItemStack getWeapon() {
        return weapon;
    }

    /**
     * @return The base damage before enchantments (vanilla variable f).
     */
    public float getBaseDamage() {
        return baseDamage;
    }

    /**
     * @return The additional enchanted damage (vanilla variable f1).
     */
    public float getEnchantedDamage() {
        return enchantedDamage;
    }

    /**
     * @return The attack strength scale (vanilla variable f2).
     */
    public float getStrengthScale() {
        return strengthScale;
    }

    /**
     * @return Whether the hit is currently considered critical.
     */
    public boolean isCritical() {
        return critical;
    }

    /**
     * @return Whether the hit was originally considered critical by vanilla logic.
     */
    public boolean wasOriginallyCritical() {
        return originallyCritical;
    }

    /**
     * Sets whether the hit should be critical.
     */
    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    /**
     * @return The multiplier applied to base damage when the hit is critical.
     * Default is 1.5 in vanilla.
     */
    public float getCriticalMultiplier() {
        return criticalMultiplier;
    }

    /**
     * Sets a custom multiplier for critical hits.
     * Ignored if {@link #isCritical()} is false.
     */
    public void setCriticalMultiplier(float multiplier) {
        this.criticalMultiplier = multiplier;
    }

    /**
     * @return The original multiplier applied to base damage when the hit is critical.
     */
    public float getOriginalCriticalMultiplier() {
        return originalCriticalMultiplier;
    }

    /**
     * @return The total post-multiplier damage (f * multiplier + f1).
     */
    public float getFinalDamage() {
        return (isCritical() ? baseDamage * criticalMultiplier : baseDamage) + enchantedDamage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
