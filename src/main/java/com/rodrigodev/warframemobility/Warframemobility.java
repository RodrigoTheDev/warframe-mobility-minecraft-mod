package com.rodrigodev.warframemobility;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rodrigodev.warframemobility.config.WarframeMobilityConfig;
import com.rodrigodev.warframemobility.movement.ability.SlideAbility;
import com.rodrigodev.warframemobility.network.SlideStatePayload;

public class Warframemobility implements ModInitializer {
	public static final String MOD_ID = "warframemobility";
	public static WarframeMobilityConfig CONFIG;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CONFIG = WarframeMobilityConfig.load();

		PayloadTypeRegistry.playC2S().register(SlideStatePayload.TYPE, SlideStatePayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(
			SlideStatePayload.TYPE,
			(payload, context) ->
				SlideAbility.setSliding(context.player().getUUID(), payload.sliding())
		);

		LOGGER.info("Hello Fabric world!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
