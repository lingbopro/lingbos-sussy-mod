package me.lingbopro.lingbossussymod.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static me.lingbopro.lingbossussymod.SussyMod.MOD_ID;
import static net.minecraft.world.item.Items.COMMAND_BLOCK;

/**
 * 创造模式标签页注册项
 */
public class CreativeTabs {
    static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    //<editor-fold desc="Tabs">
    public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("lingbos_sussy_mod",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup.lingbos_sussy_mod"),
                    () -> new ItemStack(COMMAND_BLOCK) // replace this when we have an icon item
            ));
    //</editor-fold>

    public static void register() {
        TABS.register();
    }
}
