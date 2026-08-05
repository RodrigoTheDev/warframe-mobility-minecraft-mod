package com.rodrigodev.warframemobility.client.mixin;

import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(
        method = "getFov",
        at = @At("RETURN"),
        cancellable = true
    )
    private void warframeMobility$disableDynamicFov(
            Camera camera,
            float tickDelta,
            boolean useFovSetting,
            CallbackInfoReturnable<Double> cir
    ) {

        if (!WarframeMobilityState.isEnabled()) {
            return;
        }

        double playerFov =
                Minecraft.getInstance()
                        .options
                        .fov()
                        .get();

        cir.setReturnValue(playerFov);
    }
}