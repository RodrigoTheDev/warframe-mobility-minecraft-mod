package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.Warframemobility;
import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;

public class SlideHandler {

    private static boolean sliding = false;

    private static Vec3 slideVelocity = Vec3.ZERO;

    public static void tick(LocalPlayer player) {

        if (!WarframeMobilityState.isEnabled()) {

            stopSlide(player);
            return;

        }

        boolean crouching =
            Minecraft.getInstance().options.keyShift.isDown();

        // Ainda não está deslizando
        if (!sliding) {

            if (canStartSlide(player, crouching)) {
                startSlide(player);

            }
            return;

        }

        // Mantém a pose durante todo o slide
        player.setPose(Pose.SWIMMING);

        applySlideMovement(player);

        // Soltou SHIFT
        if (!crouching) {
            stopSlide(player);
            return;

        }

        // Parou de se mover
        if (getHorizontalSpeed() <= Warframemobility.CONFIG.slideMinSpeed) {
            stopSlide(player);

        }

    }

    private static boolean canStartSlide(
            LocalPlayer player,
            boolean crouching
    ) {

        return crouching
            && player.isSprinting()
            && player.onGround();

    }

    private static void startSlide(LocalPlayer player) {

        sliding = true;


        Vec3 direction =
            new Vec3(
                player.getLookAngle().x,
                0,
                player.getLookAngle().z
            ).normalize();


        double speed =
            player.getSpeed() * 3.0;


        slideVelocity =
            direction.scale(speed);

    }


    private static void applySlideMovement(LocalPlayer player) {

        slideVelocity =
            slideVelocity.scale(
                    Warframemobility.CONFIG.slideDeceleration
            );


        Vec3 current =
            player.getDeltaMovement();


        player.setDeltaMovement(
            slideVelocity.x,
            current.y,
            slideVelocity.z
        );

    }

    private static double getHorizontalSpeed() {

        return Math.sqrt(
            slideVelocity.x * slideVelocity.x +
            slideVelocity.z * slideVelocity.z
        );

    }

    private static void stopSlide(LocalPlayer player) {

        if (!sliding) {
            return;
        }

        sliding = false;

        slideVelocity = Vec3.ZERO;

    }


    public static boolean isSliding() {
        return sliding;
    }

}