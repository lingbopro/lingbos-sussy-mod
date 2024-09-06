package me.lingbopro.sussymod;

import com.mojang.logging.LogUtils;
import me.lingbopro.sussymod.enchantment.BuildUpBlocksEnchantment;
import me.lingbopro.sussymod.item.CoinItem;
import me.lingbopro.sussymod.item.DislikeItem;
import me.lingbopro.sussymod.item.LikeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SussyMod.MODID)
public class SussyMod {
    // 定义 Mod ID
    public static final String MODID = "lingbossussymod";
    // 创建 Deferred Register 用于注册方块、物品、附魔、创造模式标签页
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // 添加方块
    public static final RegistryObject<Block> LINGBO_BLOCK = BLOCKS.register("lingbo_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(1, 24)
            .mapColor(MapColor.COLOR_LIGHT_GREEN)));
    // 添加方块物品
    public static final RegistryObject<Item> LINGBO_BLOCK_ITEM = ITEMS.register("lingbo_block", () -> new BlockItem(LINGBO_BLOCK.get(), new Item.Properties()
            .rarity(Rarity.UNCOMMON)));
    // 添加物品
    public static final RegistryObject<Item> LIKE_ITEM = ITEMS.register("like", () -> new LikeItem(new Item.Properties()));
    public static final RegistryObject<Item> DISLIKE_ITEM = ITEMS.register("dislike", () -> new DislikeItem(new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> COIN_ITEM = ITEMS.register("coin", () -> new CoinItem(new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEat().nutrition(24).saturationMod(16f)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 3), 1.0F)
                    .build())
            .fireResistant()));
    // 添加附魔
    public static final RegistryObject<Enchantment> BUILD_UP_BLOCKS_ENCHANTMENT = ENCHANTMENTS.register("build_up_blocks",
            () -> new BuildUpBlocksEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    // 添加创造模式标签页
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("lingbos_sussy_mod", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.lingbos_sussy_mod"))
            .icon(() -> LINGBO_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // 添加标签页物品
                output.accept(LINGBO_BLOCK_ITEM.get());
                output.accept(LIKE_ITEM.get());
                output.accept(DISLIKE_ITEM.get());
                output.accept(COIN_ITEM.get());
                // 附魔书
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(BUILD_UP_BLOCKS_ENCHANTMENT.get(), 1)));
            }).build());

    // 声明 slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SussyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册 commonSetup 方法用于 Mod 加载
        modEventBus.addListener(this::commonSetup);

        // 注册 Deferred Register
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

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
            event.accept(COIN_ITEM);
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
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.sendSystemMessage(Component.translatable("text.lingbossussymod.clientHello").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
                player.sendSystemMessage(Component.translatable("text.lingbossussymod.platform.bilibili").append(": ").withStyle(ChatFormatting.DARK_PURPLE)
                        .append(Component.translatable("text.lingbossussymod.url.bilibili").withStyle(ChatFormatting.DARK_BLUE)));
                player.sendSystemMessage(Component.translatable("text.lingbossussymod.platform.github").append(": ").withStyle(ChatFormatting.DARK_PURPLE)
                        .append(Component.translatable("text.lingbossussymod.url.github").withStyle(ChatFormatting.DARK_BLUE)));
                player.sendSystemMessage(Component.translatable("text.lingbossussymod.enjoy").withStyle(ChatFormatting.GOLD));
            }
        }
    }
}
