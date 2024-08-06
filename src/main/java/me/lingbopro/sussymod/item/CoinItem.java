package me.lingbopro.sussymod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoinItem extends Item {
    public CoinItem(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> componentList, @NotNull TooltipFlag tooltipFlag) {
        componentList.add(Component.translatable("tooltip.lingbossussymod.coin").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(itemStack, level, componentList, tooltipFlag);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        // 论我定义这个方法有啥用
        return super.use(level, player, interactionHand);
    }
}
