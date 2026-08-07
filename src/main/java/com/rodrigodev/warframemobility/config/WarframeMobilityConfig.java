package com.rodrigodev.warframemobility.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WarframeMobilityConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("warframemobility.json");


    public double runSpeedMultiplier = 1.5;

    public double wfModeJumpForce = 0.7;

    public double doubleJumpForce = 0.8;

    public double wallJumpForce = 0.8;

    public double bulletJumpHorizontalForce = 1.5;

    public double bulletJumpVerticalForce = 0.8;

    public int doubleJumpDelayTicks = 3;

    public double slideDeceleration = 0.95;
    
    public double slideMinSpeed = 0.05;


    public static WarframeMobilityConfig load() {

        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);

                WarframeMobilityConfig config =
                        GSON.fromJson(json, WarframeMobilityConfig.class);

                return config;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WarframeMobilityConfig config = new WarframeMobilityConfig();
        save(config);

        return config;
    }


    private static void save(WarframeMobilityConfig config) {

        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            Files.writeString(
                    CONFIG_PATH,
                    GSON.toJson(config)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}