package io.papermc.paper.event.entity;

import io.papermc.paper.event.item.enchanted.EnchantedItemInUseEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@NullMarked
public abstract class EntityEnchantedItemInUseEvent extends EntityEvent implements EnchantedItemInUseEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;
    private final ItemStack itemStack;
    private final Enchantment enchantment;
    private final int level;
    private final @Nullable LivingEntity owner;
    private final @Nullable EquipmentSlot equipmentSlot;

    @ApiStatus.Internal
    protected EntityEnchantedItemInUseEvent(
        final Entity entity,
        final ItemStack itemStack,
        final Enchantment enchantment,
        final int level,
        final @Nullable LivingEntity owner,
        final @Nullable EquipmentSlot equipmentSlot
    ) {
        super(entity);
        this.entity = entity;
        this.itemStack = itemStack;
        this.enchantment = enchantment;
        this.level = level;
        this.owner = owner;
        this.equipmentSlot = equipmentSlot;
    }

    /**
     * Gets the entity involved in this enchantment evaluation.
     *
     * @return the entity
     */
    @Override
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Yields a copy of the {@link ItemStack} that holds or contributes the enchantment.
     *
     * @return the item stack being checked
     */
    @Override
    public ItemStack getItemStack() {
        return this.itemStack.clone();
    }

    /**
     * Gets the enchantment being evaluated.
     *
     * @return the enchantment
     */
    @Override
    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    /**
     * Gets the enchantment level being evaluated.
     *
     * @return the level of the enchantment
     */
    public int getLevel() {
        return this.level;
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot() {
        return this.equipmentSlot;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
