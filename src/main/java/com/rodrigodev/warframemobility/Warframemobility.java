package com.rodrigodev.warframemobility;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rodrigodev.warframemobility.config.WarframeMobilityConfig;

public class Warframemobility implements ModInitializer {
	public static final String MOD_ID = "warframemobility";
	public static WarframeMobilityConfig CONFIG;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CONFIG = WarframeMobilityConfig.load();

		LOGGER.info("Hello Fabric world!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
