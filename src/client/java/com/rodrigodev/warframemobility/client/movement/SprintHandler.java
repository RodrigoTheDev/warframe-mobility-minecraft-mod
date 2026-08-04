package com.rodrigodev.warframemobility.client.movement;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import com.rodrigodev.warframemobility.Warframemobility;
import com.rodrigodev.warframemobility.client.WarframeMobilityState;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;

public class SprintHandler {

    private static final ResourceLocation SPRINT_MODIFIER_ID =
        ResourceLocation.fromNamespaceAndPath(
                "warframemobility",
                "sprint"
        );
    
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            applySprintBoost(client);
        });
    }

    public static void applySprintBoost(Minecraft client) {
        if (client.player == null) {
            return;
        }
        if (!WarframeMobilityState.isEnabled()) {
            removeSprintModifier(client);
            return;
        }

        if (client.player.isSprinting()) {
            addSprintModifier(client);
        } else {
            removeSprintModifier(client);
        }
    }

    private static void addSprintModifier(Minecraft client) {

        var attribute =
                client.player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attribute == null) {
            return;
        }

        if (attribute.getModifier(SPRINT_MODIFIER_ID) != null) {
            return;
        }

        AttributeModifier modifier =
        new AttributeModifier(
                SPRINT_MODIFIER_ID,
                Warframemobility.CONFIG.runSpeedMultiplier - 1.0,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        );

        attribute.addTransientModifier(modifier);
    }

    private static void removeSprintModifier(Minecraft client) {

        var attribute =
                client.player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attribute == null) {
            return;
        }

        if (attribute.getModifier(SPRINT_MODIFIER_ID) != null) {
            attribute.removeModifier(SPRINT_MODIFIER_ID);
        }
    }

    
}
