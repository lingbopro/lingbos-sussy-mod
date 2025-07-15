package me.lingbopro.lingbossussymod.fabric;

import me.lingbopro.lingbossussymod.SussyMod;
import net.fabricmc.api.ModInitializer;

public final class SussyModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        SussyMod.init();
    }
}
