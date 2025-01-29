package me.lingbopro.sussymod.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.lingbopro.sussymod.SussyMod.MODID;

/**
 * 方块物品注册项
 */
public abstract class BlockItems {
    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    //<editor-fold desc="Block Items">
    public static final RegistryObject<Item> LINGBO_BLOCK_ITEM = ITEMS.register("lingbo_block", () -> new BlockItem(Blocks.LINGBO_BLOCK.get(),
            new Item.Properties()
                    .rarity(Rarity.UNCOMMON)));
    //</editor-fold>

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
