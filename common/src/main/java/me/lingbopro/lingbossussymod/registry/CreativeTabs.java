package me.lingbopro.lingbossussymod.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import static me.lingbopro.lingbossussymod.SussyMod.MOD_ID;

/**
 * 创造模式标签页注册项
 */
public class CreativeTabs {
    static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    //<editor-fold desc="Tabs">
    public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("lingbos_sussy_mod",
            () -> CreativeTabRegistry.create(builder -> {
                        builder.title(Component.translatable("itemGroup.lingbos_sussy_mod"));
                        builder.icon(() -> new ItemStack(Items.SUSSY_CORE));
                        builder.displayItems((parameters, output) -> {
                            output.accept((ItemLike) Items.SUSSY_CORE);
                            output.accept((ItemLike) Items.COIN);
                        });
                    }
            ));
    //</editor-fold>

    public static void register() {
        TABS.register();
    }
}
