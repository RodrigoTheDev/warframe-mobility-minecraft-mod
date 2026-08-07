package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.Warframemobility;
import com.rodrigodev.warframemobility.client.WarframeMobilityState;
import com.rodrigodev.warframemobility.client.mixin.LivingEntityAccessor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class BulletJumpHandler {

    private static boolean jumpWasPressedLastTick = false;

    private static int bulletJumpAnimationTicks = 0;

    public static void tick(LocalPlayer player) {

        tickAnimation(player);

        if (!WarframeMobilityState.isEnabled()) {
            jumpWasPressedLastTick = false;
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        boolean jumpPressed =
                minecraft.options.keyJump.isDown();

        boolean crouching =
                minecraft.options.keyShift.isDown();

        boolean justPressed =
                jumpPressed && !jumpWasPressedLastTick;

        jumpWasPressedLastTick = jumpPressed;

        if (!justPressed) {
            return;
        }

        /*
         * Bullet Jump precisa de agachar.
         */
        if (!crouching) {
            return;
        }

        /*
         * Usa o mesmo recurso do Double Jump.
         */
        if (!DoubleJumpHandler.canDoubleJump()) {
            return;
        }

        performBulletJump(player);
    }

    private static void performBulletJump(LocalPlayer player) {

        Vec3 look =
                player.getLookAngle().normalize();

        double horizontalForce =
                Warframemobility.CONFIG.bulletJumpHorizontalForce;

        double verticalForce =
                Warframemobility.CONFIG.bulletJumpVerticalForce;

        Vec3 velocity = new Vec3(
                look.x * horizontalForce,
                look.y * horizontalForce + verticalForce,
                look.z * horizontalForce
        );

        player.setDeltaMovement(velocity);

        player.hasImpulse = true;

        /*
         * Consome o salto aéreo.
         */
        DoubleJumpHandler.consumeDoubleJump();

        /*
         * Inicia animação vanilla do Riptide.
         */
        startBulletJumpAnimation(player);

        /*
         * Se estava deslizando, encerra.
         */
        if (SlideHandler.isSliding()) {
            SlideHandler.forceStop(player);
        }

        System.out.println("Bullet Jump!");
    }

    private static void startBulletJumpAnimation(LocalPlayer player) {

        ((LivingEntityAccessor) player)
                .warframeMobility$setLivingEntityFlag(4, true);

        bulletJumpAnimationTicks = 10;
    }

    private static void tickAnimation(LocalPlayer player) {

        if (bulletJumpAnimationTicks <= 0) {
            return;
        }

        bulletJumpAnimationTicks--;

        if (bulletJumpAnimationTicks == 0) {

            ((LivingEntityAccessor) player)
                    .warframeMobility$setLivingEntityFlag(4, false);

        }
    }

    public static boolean isBulletJumping() {
        return bulletJumpAnimationTicks > 0;
    }
}