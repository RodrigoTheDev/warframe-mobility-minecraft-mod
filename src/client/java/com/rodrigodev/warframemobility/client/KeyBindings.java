package com.rodrigodev.warframemobility.client;

import org.lwjgl.glfw.GLFW;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class KeyBindings {
    public static KeyMapping toggleWarframeMode;

    private KeyBindings() {
        
    }

    public static void register() {
        toggleWarframeMode = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.warfarmemobility.toggle", GLFW.GLFW_KEY_G, "category.warframemobility")
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleWarframeMode.consumeClick()) {
                WarframeMobilityState.toggle();
            }
        });
    }
}
