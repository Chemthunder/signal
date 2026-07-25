package org.autumn.signal.core.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.autumn.signal.core.networking.s2c.FlashPayload;

/**
 * @author Chemthunder
 */
public interface SignalNetworking {
    static void register() {
        PayloadTypeRegistry.playS2C().register(FlashPayload.ID, FlashPayload.CODEC);
    }

    @Environment(EnvType.CLIENT)
    static void s2c() {
        ClientPlayNetworking.registerGlobalReceiver(FlashPayload.ID, new FlashPayload.Receiver());
    }
}
