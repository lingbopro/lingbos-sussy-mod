package me.lingbopro.sussymod.registry;

import me.lingbopro.sussymod.item.CoinItem;
import me.lingbopro.sussymod.item.DislikeItem;
import me.lingbopro.sussymod.item.LikeItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.lingbopro.sussymod.SussyMod.MODID;

/**
 * 物品注册项
 */
public abstract class Items {
    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    //<editor-fold desc="Items">
    public static final RegistryObject<Item> LIKE_ITEM = ITEMS.register("like", () -> new LikeItem(new Item.Properties()));
    public static final RegistryObject<Item> DISLIKE_ITEM = ITEMS.register("dislike", () -> new DislikeItem(new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> COIN_ITEM = ITEMS.register("coin", () -> new CoinItem(new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEat().nutrition(24).saturationMod(16f)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 3), 1.0F)
                    .build())
            .fireResistant()));
    //</editor-fold>

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
