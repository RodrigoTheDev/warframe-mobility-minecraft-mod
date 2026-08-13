package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.Warframemobility;
import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class WallJumpHandler {

    private static boolean jumpWasPressedLastTick = false;

    public static void tick(LocalPlayer player) {

        if (!WarframeMobilityState.isEnabled()) {
            jumpWasPressedLastTick = false;
            return;
        }

        boolean jumpPressed = Minecraft.getInstance().options.keyJump.isDown();

        boolean justPressed = jumpPressed && !jumpWasPressedLastTick;

        jumpWasPressedLastTick = jumpPressed;

        if (!justPressed) {
            return;
        }

        if (player.onGround()) {
            return;
        }

        if (!player.horizontalCollision) {
            return;
        }

        performWallJump(player);
    }

    private static void performWallJump(LocalPlayer player) {
        player.setDeltaMovement(
            player.getDeltaMovement().x,
            Warframemobility.CONFIG.wallJumpForce,
            player.getDeltaMovement().z
        );

        player.hasImpulse = true;
    }
}
