package me.lingbopro.sussymod.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.extensions.IForgeEnchantment;
import org.jetbrains.annotations.NotNull;

public class FlyUpEnchantment extends Enchantment implements IForgeEnchantment {
    public FlyUpEnchantment(Enchantment.Rarity rarity, EnchantmentCategory category, EquipmentSlot... equipmentSlots) {
        super(rarity, category, equipmentSlots);
    }

    public static int MAX_LEVEL = 6;

    public static void onAttack(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {
        // 在服务器端执行
        if (!attacker.level().isClientSide()) {
            // 计算Y轴移动距离
            double yMovement = 0.5 + 0.5 * Math.pow(1.25, level);
            // 让实体飞起来！
            target.setDeltaMovement(0, yMovement, 0);
            // 物品冷却
            ((Player) attacker).getCooldowns().addCooldown(attacker.getUseItem().getItem(), 30);
        }
    }

    @Override
    public void doPostAttack(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {
        onAttack(attacker, target, level);
    }

    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
