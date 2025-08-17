package me.lingbopro.lingbossussymod.registry;

import me.lingbopro.lingbossussymod.SussyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 附魔注册项
 */
public abstract class Enchantments {
    //<editor-fold desc="Enchantments">
    public static final ResourceKey<Enchantment> FLY_UP = of("fly_up");
    //</editor-fold>

    /**
     * 创建一个附魔资源键
     */
    // https://github.com/Command17/Hammering/blob/79d4df85d98082cd226f0f8c96d150c5e276877a/common/src/main/java/com/github/command17/hammering/enchantment/ModEnchantments.java#L11-L13
    public static ResourceKey<Enchantment> of(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, SussyMod.resource(name));
    }

    public static void register() {
        // 占位，确保此类中的其它东西被调用
    }
}
