package me.lingbopro.lingbossussymod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.lingbopro.lingbossussymod.item.SussyCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import static me.lingbopro.lingbossussymod.SussyMod.MOD_ID;

/**
 * 物品注册项
 */
public abstract class Items {
    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    //<editor-fold desc="Items">
    public static final RegistrySupplier<Item> SUSSY_CORE = ITEMS.register("sussy_core", SussyCore::new);
    //</editor-fold>

    public static void register() {
        ITEMS.register();
    }
}
