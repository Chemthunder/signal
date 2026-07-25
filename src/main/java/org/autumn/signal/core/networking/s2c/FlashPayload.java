package org.autumn.signal.core.networking.s2c;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.client.event.FlashEvents;

/**
 * @author Chemthunder
 */
public record FlashPayload() implements CustomPayload {
    public static final CustomPayload.Id<FlashPayload> ID = new CustomPayload.Id<>(Signal.id("flash"));

    public static final PacketCodec<RegistryByteBuf, FlashPayload> CODEC = PacketCodec.unit(new FlashPayload());

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<FlashPayload> {
        public void receive(FlashPayload payload, ClientPlayNetworking.Context context) {
            context.client().execute(() -> FlashEvents.Ticker.opacity = 1.0F);
        }
    }
}
