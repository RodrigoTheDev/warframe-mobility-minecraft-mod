package com.rodrigodev.warframemobility.client.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.rodrigodev.warframemobility.client.movement.DoubleJumpHandler;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void warframeMobility$onTick(CallbackInfo ci) {
        DoubleJumpHandler.tick((LocalPlayer) (Object) this);
    }
}