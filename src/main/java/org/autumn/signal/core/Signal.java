package org.autumn.signal.core;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;

import org.autumn.signal.core.command.SignalCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Signal implements ModInitializer {
	public static final String MOD_ID = "signal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(new SignalCommand());

		LOGGER.info("Signaling it");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
