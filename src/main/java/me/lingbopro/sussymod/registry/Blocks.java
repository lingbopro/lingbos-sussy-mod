package me.lingbopro.sussymod.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.lingbopro.sussymod.SussyMod.MODID;

/**
 * 方块注册项
 */
public abstract class Blocks {
    static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);


    //<editor-fold desc="Blocks">
    public static final RegistryObject<Block> LINGBO_BLOCK = BLOCKS.register("lingbo_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(1, 24)
            .mapColor(MapColor.COLOR_LIGHT_GREEN)));
    //</editor-fold>

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
