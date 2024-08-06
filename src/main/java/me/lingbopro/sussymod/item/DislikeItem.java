package me.lingbopro.sussymod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DislikeItem extends Item {
    public DislikeItem(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> componentList, @NotNull TooltipFlag tooltipFlag) {
        componentList.add(Component.translatable("tooltip.lingbossussymod.dislike").withStyle(ChatFormatting.DARK_RED));
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            // 本来想直接秒杀，但是这样太没有游戏体验了
            player.hurt(player.damageSources().genericKill(), 3);
            // 生成激活的TNT
            PrimedTnt tnt = new PrimedTnt(level, player.getX(), player.getY(), player.getZ(), null);
            level.addFreshEntity(tnt);
        }
        // 冷却
        player.getCooldowns().addCooldown(this, 200);
        return super.use(level, player, interactionHand);
    }
}
