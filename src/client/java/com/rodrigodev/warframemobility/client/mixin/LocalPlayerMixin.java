package com.rodrigodev.warframemobility.client.mixin;

import com.rodrigodev.warframemobility.client.movement.BulletJumpHandler;
import com.rodrigodev.warframemobility.client.movement.DoubleJumpHandler;
import com.rodrigodev.warframemobility.client.movement.SlideHandler;
import com.rodrigodev.warframemobility.client.movement.StepHeightHandler;
import com.rodrigodev.warframemobility.client.movement.WallJumpHandler;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void warframeMobility$onTick(CallbackInfo ci) {

        LocalPlayer player = (LocalPlayer) (Object) this;

        DoubleJumpHandler.tick(player);
        SlideHandler.tick(player);
        BulletJumpHandler.tick(player);
        WallJumpHandler.tick(player);
        StepHeightHandler.tick(player);

    }

}