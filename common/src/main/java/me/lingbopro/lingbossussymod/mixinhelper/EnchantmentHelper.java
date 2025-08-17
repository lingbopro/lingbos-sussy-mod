package me.lingbopro.lingbossussymod.mixinhelper;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Optional;

public class EnchantmentHelper {
    // https://github.com/Mafuyu33/neomafishmod/blob/329cfae584ad7e5bf4de30637772625a92d07770/src/main/java/com/mafuyu33/neomafishmod/mixinhelper/InjectHelper.java#L70C5-L80C6
    public static int getEnchantmentLevel(ItemStack item, ResourceKey<Enchantment> enchantmentResourceKey){
        if (item == null){
            return 0;
        }
        ItemEnchantments itemEnchantments = item.get(DataComponents.ENCHANTMENTS);
        if (itemEnchantments == null) {
            return -1;
        }
        Optional<Object2IntMap.Entry<Holder<Enchantment>>> levelOptional = itemEnchantments.entrySet().stream().filter(int2Enchantment -> int2Enchantment.getKey().is(enchantmentResourceKey)).findFirst();
        return levelOptional.map(Object2IntMap.Entry::getIntValue).orElse(-1);
    }
}
