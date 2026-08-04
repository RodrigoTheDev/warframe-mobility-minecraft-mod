package com.rodrigodev.warframemobility.client;

import net.fabricmc.api.ClientModInitializer;

public class WarframemobilityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		KeyBindings.register();
	}
}