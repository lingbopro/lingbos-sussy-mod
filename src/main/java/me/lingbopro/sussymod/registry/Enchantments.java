package me.lingbopro.sussymod.registry;

import me.lingbopro.sussymod.enchantment.FlyUpEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.lingbopro.sussymod.SussyMod.MODID;

/**
 * 附魔注册项
 */
public abstract class Enchantments {
    static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    //<editor-fold desc="Enchantments">
    public static final RegistryObject<Enchantment> FLY_UP_ENCHANTMENT = ENCHANTMENTS.register("fly_up",
            () -> new FlyUpEnchantment(Enchantment.Rarity.RARE,
                    EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
    //</editor-fold>

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
