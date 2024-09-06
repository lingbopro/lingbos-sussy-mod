package me.lingbopro.sussymod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class BuildUpBlocksEnchantment extends Enchantment {
    public BuildUpBlocksEnchantment(Enchantment.Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.WEAPON, equipmentSlots);
    }

    public static void onAttack(@NotNull LivingEntity attacker, int level) {
        //在服务器端执行
        if (!attacker.level().isClientSide()) {
            if (attacker instanceof Player) {
                //获取攻击者站立的位置
                BlockPos pos = attacker.getOnPos();
                double attackerY = attacker.getY();
                // 如果玩家站在圆石上则不搭方块
                if (attacker.level().getBlockState(pos).is(Blocks.COBBLESTONE)) {
                    return;
                }
                if (level > 0) {
                    // 计算要搭多少格方块
                    int blockAmount = 2;
                    // 每多一级搭的方块数×1.25（向上取整）
                    for (int i = 1; i < level; i++) {
                        blockAmount = (int) Math.ceil(blockAmount * 1.25);
                    }
                    // 搭的方块的最高点
                    int topY = (int) (attackerY + blockAmount);
                    // 计算是否顶头
                    for (int i = 0; i < blockAmount; i++) {
                        // 玩家头上方块的Y
                        int headY = (int) (attackerY + i + 2);
                        BlockPos headPos = new BlockPos(pos.getX(), headY, pos.getZ());
                        if (!attacker.level().getBlockState(headPos).isAir()) {
                            // 顶头
                            topY = (int) (attackerY + i);
                        }
                    }
                    // 传送玩家
                    attacker.teleportTo(attacker.getX(), topY + 1, attacker.getZ());
                    // 搭方块
                    for (int i = 0; i < blockAmount; i++) {
                        int y = (int) (attackerY + i);
                        BlockPos blockPos = new BlockPos(pos.getX(), y, pos.getZ());
                        // 本来想写消耗玩家背包物品，但是写着太麻烦，就直接用原石了
                        attacker.level().setBlockAndUpdate(blockPos, Blocks.COBBLESTONE.defaultBlockState());
                    }
                }
                ((Player) attacker).getCooldowns().addCooldown(attacker.getUseItem().getItem(), 10);
            }
        }
    }

    @Override
    public void doPostAttack(@NotNull LivingEntity attacker, @NotNull Entity target, int level) {
        onAttack(attacker, level);
    }

    //附魔的最大等级，从1开始
    @Override
    public int getMaxLevel() {
        return 3;
    }
}
