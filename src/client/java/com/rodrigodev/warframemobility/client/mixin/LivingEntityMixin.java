package com.rodrigodev.warframemobility.client.mixin;

import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public class LivingEntityMixin {


    @Inject(
        method = "causeFallDamage",
        at = @At("HEAD"),
        cancellable = true
    )
    private void warframeMobility$disableFallDamage(
            float fallDistance,
            float damageMultiplier,
            DamageSource damageSource,
            CallbackInfoReturnable<Boolean> cir
    ) {

        if (!WarframeMobilityState.isEnabled()) {
            return;
        }

        cir.setReturnValue(false);
    }
}