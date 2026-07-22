package org.autumn.signal.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.autumn.signal.core.event.client.SignalHudEvent;

/**
 * @author Chemthunder
 */
public class SignalClient implements ClientModInitializer {
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new SignalHudEvent());
    }
}
