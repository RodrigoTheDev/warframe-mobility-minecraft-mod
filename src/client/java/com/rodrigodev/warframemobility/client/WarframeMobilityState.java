package com.rodrigodev.warframemobility.client;

public class WarframeMobilityState {

    private static boolean enabled = false;

    public static void toggle() {
        enabled = !enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
