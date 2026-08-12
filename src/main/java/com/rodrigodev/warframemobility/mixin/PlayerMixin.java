package com.rodrigodev.warframemobility.mixin;

import com.rodrigodev.warframemobility.movement.ability.SlideAbility;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    // Roda em ambos os lados (client e servidor) para que a pose SWIMMING do
    // slide seja aplicada de forma consistente, evitando que o servidor
    // rejeite o movimento por ver uma hitbox diferente da do cliente.
    @Inject(
        method = "updatePlayerPose",
        at = @At("HEAD"),
        cancellable = true
    )
    private void warframeMobility$forceSlidePose(CallbackInfo ci) {

        Player self = (Player) (Object) this;

        if (!SlideAbility.isSliding(self.getUUID())) {
            return;
        }

        self.setPose(Pose.SWIMMING);
        ci.cancel();
    }
}
