package me.lingbopro.lingbossussymod.mixin.enchantment;

import me.lingbopro.lingbossussymod.mixinhelper.EnchantmentHelper;
import me.lingbopro.lingbossussymod.registry.Enchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class WeaponEnchantmentMixin extends Entity implements Attackable {
    public WeaponEnchantmentMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    @Shadow
    public abstract InteractionHand getUsedItemHand();

    // https://github.com/Mafuyu33/neomafishmod/blob/bca661c8e6055ccdad4f0b9f97c5a8bdfa680fe4/src/main/java/com/mafuyu33/neomafishmod/mixin/enchantmentitemmixin/WeaponEnchantmentMixin.java#L71-L75
    @Inject(at = @At("RETURN"), method = "setLastHurtMob")
    private void onAttack(Entity target, CallbackInfo info) {
        InteractionHand hand = this.getUsedItemHand();
        ItemStack itemStack = this.getItemInHand(hand);
        LivingEntity thisObj = (LivingEntity) (Object) this;

        //region 飞起来！
        {
            final int level = EnchantmentHelper.getEnchantmentLevel(itemStack, Enchantments.FLY_UP);
            if (level > 0) {
                double yMovement = 0.5 + 0.5 * Math.pow(1.25, level);
                target.setDeltaMovement(0, yMovement, 0);
                if (thisObj instanceof Player player) {
                    player.getCooldowns().addCooldown(itemStack.getItem(), 30);
                }
            }
        }
        //endregion
    }

}
