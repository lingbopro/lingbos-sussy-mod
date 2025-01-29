package me.lingbopro.sussymod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static me.lingbopro.sussymod.SussyMod.MODID;

/**
 * 创造模式标签页注册项
 */
public abstract class CreativeTabs {
    static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    //<editor-fold desc="Creative Tabs">
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("lingbos_sussy_mod", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.lingbos_sussy_mod"))
            .icon(() -> BlockItems.LINGBO_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // 添加标签页物品
                output.accept(BlockItems.LINGBO_BLOCK_ITEM.get());
                output.accept(Items.LIKE_ITEM.get());
                output.accept(Items.DISLIKE_ITEM.get());
                output.accept(Items.COIN_ITEM.get());
            }).build());
    //</editor-fold>

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
