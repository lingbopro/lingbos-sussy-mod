package me.lingbopro.lingbossussymod;

import com.mojang.logging.LogUtils;
import me.lingbopro.lingbossussymod.registry.CreativeTabs;
import me.lingbopro.lingbossussymod.registry.Items;
import org.slf4j.Logger;

public final class SussyMod {
    public static final String MOD_ID = "lingbossussymod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        // Write common init code here.
        LOGGER.info("[Lingbo's Sussy Mod] We're up and running!");

        // 注册项目
        Items.register();
        CreativeTabs.register();
    }
}
