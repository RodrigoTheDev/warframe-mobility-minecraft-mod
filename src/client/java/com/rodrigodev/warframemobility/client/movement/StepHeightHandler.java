package com.rodrigodev.warframemobility.client.movement;

import com.rodrigodev.warframemobility.client.WarframeMobilityState;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StepHeightHandler {

    private static final ResourceLocation WARFRAME_STEP_HEIGHT_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(
                    "warframemobility",
                    "step_height"
            );

    private static final double STEP_HEIGHT_BONUS = 0.4;

    public static void tick(LocalPlayer player) {
        AttributeInstance attribute =
                player.getAttribute(Attributes.STEP_HEIGHT);

        if (attribute == null) {
            return;
        }

        AttributeModifier modifier =
                attribute.getModifier(WARFRAME_STEP_HEIGHT_MODIFIER_ID);

        if (WarframeMobilityState.isEnabled()) {
            if (modifier == null) {
                modifier = new AttributeModifier(
                        WARFRAME_STEP_HEIGHT_MODIFIER_ID,
                        STEP_HEIGHT_BONUS,
                        AttributeModifier.Operation.ADD_VALUE
                );

                attribute.addTransientModifier(modifier);
            }
        } else {
            if (modifier != null) {
                attribute.removeModifier(modifier);
            }
        }
    }
}