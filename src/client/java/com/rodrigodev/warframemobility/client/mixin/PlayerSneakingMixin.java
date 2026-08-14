package com.rodrigodev.warframemobility.client.mixin;

import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerSneakingMixin {

    @Inject(
        method = "maybeBackOffFromEdge",
        at = @At("HEAD"),
        cancellable = true
    )
    private void warframeMobility$disableEdgeProtection(
        Vec3 movement,
        MoverType moverType,
        CallbackInfoReturnable<Vec3> cir 
    ) {
        if (!WarframeMobilityState.isEnabled()) {
            return;
        }

        cir.setReturnValue(movement);
    }
}
