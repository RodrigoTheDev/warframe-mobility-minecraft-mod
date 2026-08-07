package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.Warframemobility;
import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class DoubleJumpHandler {

    private static boolean doubleJumpUsed = false;
    private static boolean jumpWasPressedLastTick = false;
    private static boolean wasOnGround = true;

    private static int doubleJumpCooldownTicks = 0;

    public static void tick(LocalPlayer player) {

        boolean jumpPressed = Minecraft.getInstance().options.keyJump.isDown();

        boolean justPressed = jumpPressed && !jumpWasPressedLastTick;

        jumpWasPressedLastTick = jumpPressed;

        // Controle do estado do jogador
        if (player.onGround()) {

            wasOnGround = true;
            doubleJumpUsed = false;

        } else {

            if (wasOnGround) {
                
                if(jumpWasPressedLastTick && WarframeMobilityState.isEnabled()) {
                    applyWarframeJumpForce(player);
                }
                wasOnGround = false;
                doubleJumpCooldownTicks =
                        Warframemobility.CONFIG.doubleJumpDelayTicks;
            }

            if (doubleJumpCooldownTicks > 0) {
                doubleJumpCooldownTicks--;
            }
        }

        if (!WarframeMobilityState.isEnabled()) {
            return;
        }

        if (!justPressed) {
            return;
        }

        // Ignora o pulo normal
        if (player.onGround()) {
            return;
        }

        // Evita ativar imediatamente após sair do chão
        if (doubleJumpCooldownTicks > 0) {
            return;
        }


        // Só permite uma vez por salto
        if (doubleJumpUsed) {
            return;
        }


        performDoubleJump(player);
    }


    private static void performDoubleJump(LocalPlayer player) {

        doubleJumpUsed = true;

        player.setDeltaMovement(
                player.getDeltaMovement().x,
                Warframemobility.CONFIG.doubleJumpForce,
                player.getDeltaMovement().z
        );

        player.hasImpulse = true;
    }

    private static void applyWarframeJumpForce(LocalPlayer player) {

        player.setDeltaMovement(
            player.getDeltaMovement().x,
            Warframemobility.CONFIG.wfModeJumpForce,
            player.getDeltaMovement().z
        );

        player.hasImpulse = true;
    }

    public static boolean canDoubleJump() {
        return !doubleJumpUsed;
    }

    public static boolean hasJumpStarted() {
        return !wasOnGround;
    }

    public static void consumeDoubleJump() {
        doubleJumpUsed = true;
    }
}