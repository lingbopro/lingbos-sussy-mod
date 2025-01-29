package me.lingbopro.sussymod;

import com.mojang.logging.LogUtils;
import me.lingbopro.sussymod.registry.*;
import me.lingbopro.sussymod.registry.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SussyMod.MODID)
public class SussyMod {
    // 定义 Mod ID
    public static final String MODID = "lingbossussymod";

    SharedVariables sharedVariables = SharedVariables.getInstance();

    // 声明 slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SussyMod() {
        sharedVariables.setLogger(LOGGER);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册 commonSetup 方法用于 Mod 加载
        modEventBus.addListener(this::commonSetup);

        // 注册 Deferred Register
        Blocks.register(modEventBus);
        BlockItems.register(modEventBus);
        Items.register(modEventBus);
        CreativeTabs.register(modEventBus);

        // 在服务器注册
        MinecraftForge.EVENT_BUS.register(this);

        // 注册原版创造标签页
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // 一些启动代码
        LOGGER.info("[LINGBO'S SUSSY MOD] Common Setup");
    }

    // 添加到原版创造物品栏标签页
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
            event.accept(Items.COIN_ITEM);
    }

    // 使用 SubscribeEvent 使事件总线自动发现要调用的方法
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("[LINGBO'S SUSSY MOD] Server Starting");
    }

    // 可以使用 EventBusSubscriber 自动注册带有 @SubscribeEvent 注释的类中的所有静态方法
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // 一些客户端启动代码
            LOGGER.info("[LINGBO'S SUSSY MOD] Client Setup");
            LOGGER.info("[LINGBO'S SUSSY MOD] MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
