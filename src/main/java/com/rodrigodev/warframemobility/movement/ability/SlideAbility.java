package com.rodrigodev.warframemobility.movement.ability;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// Estado de slide replicado entre cliente e servidor, para que a pose
// (Pose.SWIMMING) seja aplicada dos dois lados e a colisão fique consistente.
public class SlideAbility {

    private static final Set<UUID> SLIDING_PLAYERS = ConcurrentHashMap.newKeySet();

    public static void setSliding(UUID playerId, boolean sliding) {

        if (sliding) {
            SLIDING_PLAYERS.add(playerId);
        } else {
            SLIDING_PLAYERS.remove(playerId);
        }

    }

    public static boolean isSliding(UUID playerId) {
        return SLIDING_PLAYERS.contains(playerId);
    }

}
