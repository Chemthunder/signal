package org.autumn.signal.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import org.autumn.signal.core.client.event.ConsoleEvent;
import org.autumn.signal.core.client.event.DropshipRenderEvent;
import org.autumn.signal.core.client.event.FlashEvents;
import org.autumn.signal.core.client.event.SignalHudEvent;
import org.autumn.signal.core.index.SignalModelLayers;
import org.autumn.signal.core.index.SignalParticleTypes;
import org.autumn.signal.core.networking.SignalNetworking;

/**
 * @author Chemthunder
 */
public class SignalClient implements ClientModInitializer {
    public static int GLOBAL_AGE = 0;

    public void onInitializeClient() {
        SignalModelLayers.clientInit();
        SignalParticleTypes.clientInit();

        SignalNetworking.s2c();

        HudRenderCallback.EVENT.register(new SignalHudEvent());
        HudRenderCallback.EVENT.register(new ConsoleEvent());
        HudRenderCallback.EVENT.register(new FlashEvents.Renderer());

        WorldRenderEvents.LAST.register(new DropshipRenderEvent());

        ClientTickEvents.START_CLIENT_TICK.register((minecraftClient -> GLOBAL_AGE++));
        ClientTickEvents.END_CLIENT_TICK.register(new FlashEvents.Ticker());
    }
}
