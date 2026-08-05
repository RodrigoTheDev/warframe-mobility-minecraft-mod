package com.rodrigodev.warframemobility.client;

import com.rodrigodev.warframemobility.client.movement.DoubleJumpHandler;
import com.rodrigodev.warframemobility.client.movement.SprintHandler;

import net.fabricmc.api.ClientModInitializer;

public class WarframemobilityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyBindings.register();
		SprintHandler.register();
	}
}