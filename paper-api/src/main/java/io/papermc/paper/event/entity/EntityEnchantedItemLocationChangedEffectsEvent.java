package io.papermc.paper.event.entity;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class EntityEnchantedItemLocationChangedEffectsEvent extends EntityEnchantedItemInUseEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LivingEntity entity;
    private int overrideLevel;
    private Result contextResult = Result.DEFAULT;

    private boolean cancelled = false;

    @ApiStatus.Internal
    public EntityEnchantedItemLocationChangedEffectsEvent(
        final LivingEntity entity,
        final ItemStack itemStack,
        final Enchantment enchantment,
        final int enchantmentLevel,
        final @Nullable LivingEntity owner,
        final @Nullable EquipmentSlot equipmentSlot
    ) {
        super(entity, itemStack, enchantment, enchantmentLevel, owner, equipmentSlot);
        this.entity = entity;
    }

    @Override
    public LivingEntity getEntity() {
        return this.entity;
    }

    public int getOverrideLevel() {
        return this.overrideLevel;
    }

    public void setOverrideLevel(final int overrideLevel) {
        this.overrideLevel = overrideLevel;
    }

    public Result getContextResult() {
        return this.contextResult;
    }

    public void setContextResult(final Result contextResult) {
        this.contextResult = contextResult;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
