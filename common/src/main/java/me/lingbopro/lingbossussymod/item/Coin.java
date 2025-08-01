package me.lingbopro.lingbossussymod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class Coin extends Item {
    public Coin() {
        this(new Properties().food(new FoodProperties.Builder()
                .alwaysEdible().nutrition(2).saturationModifier(0.5f)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1.0F)
                .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0), 0.8F)
                .effect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 0), 0.5F)
                .build())
        );
    }

    public Coin(Properties p) {
        super(p);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.lingbossussymod.coin"));
    }
}
