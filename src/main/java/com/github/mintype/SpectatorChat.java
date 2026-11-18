package com.github.mintype;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectatorChat implements ModInitializer {
	public static final String MOD_ID = "spectator-chat";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, params) -> {
            String rawText = message.signedContent();

            MinecraftServer server = sender.level().getServer();

            boolean isOperator =
                    server.getPlayerList().isOp(sender.nameAndId());

            boolean isSpectator =
                    sender.gameMode.getGameModeForPlayer()
                            == GameType.SPECTATOR;

            // If sender is a spectator then their chat messages should only be visible (sent to) other spectators and operators

            if (isSpectator) {

            }

//            if (rawText.contains("blockedword")) {
//                sender.sendSystemMessage(Component.literal("Your message was blocked!"));
//                return false; // Cancels the chat message
//            }

            return true; // Allows the message to broadcast normally
        });

		LOGGER.info(MOD_ID + " initialized.");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
