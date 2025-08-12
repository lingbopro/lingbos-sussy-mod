package me.lingbopro.lingbossussymod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static me.lingbopro.lingbossussymod.registry.CreativeTabs.TAB;

public class SussyCore extends Item {
    public SussyCore() {
        this(new Properties().arch$tab(TAB));
    }
    public SussyCore(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.lingbossussymod.sussy_core"));
    }
}
