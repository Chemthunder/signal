package org.autumn.signal.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.autumn.signal.core.event.client.DropshipRenderEvent;
import org.autumn.signal.core.event.client.SignalHudEvent;

/**
 * @author Chemthunder
 */
public class SignalClient implements ClientModInitializer {
    public static int GLOBAL_AGE = 0;

    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new SignalHudEvent());

        DropshipRenderEvent.create();

        ClientTickEvents.START_CLIENT_TICK.register((minecraftClient -> GLOBAL_AGE++));
    }
}
