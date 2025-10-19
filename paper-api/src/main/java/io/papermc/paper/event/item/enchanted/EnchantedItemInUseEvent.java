package io.papermc.paper.event.item.enchanted;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface EnchantedItemInUseEvent {

    ItemStack getItemStack();

    Enchantment getEnchantment();

    @Nullable LivingEntity getOwner();

    @Nullable EquipmentSlot getEquipmentSlot();
}
