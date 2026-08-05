package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class DoubleJumpHandler {

    private static boolean doubleJumpUsed = false;
    private static boolean jumpWasPressedLastTick = false;
    private static boolean hasLeftGround = false;

    public static void tick(LocalPlayer player) {
        
    }

    private static void performDoubleJump(Minecraft client) {
        doubleJumpUsed = true;
    }
}
