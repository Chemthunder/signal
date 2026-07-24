package org.autumn.signal.core.client.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.util.PortingUtils;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.autumn.signal.core.index.SignalParticleTypes;

/**
 * @author Chemthunder
 */
public record StarParticleEffect(float size, int maxAge, float turnRate, float upwardsAx) implements ParticleEffect {
    public static final MapCodec<StarParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("size").forGetter(StarParticleEffect::size),
            Codec.INT.fieldOf("maxAge").forGetter(StarParticleEffect::maxAge),
            Codec.FLOAT.fieldOf("turnRate").forGetter(StarParticleEffect::turnRate),
            Codec.FLOAT.fieldOf("upwardsAx").forGetter(StarParticleEffect::upwardsAx)
    ).apply(instance, StarParticleEffect::new));

    public static final PacketCodec<RegistryByteBuf, StarParticleEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT,
            StarParticleEffect::size,
            PacketCodecs.INTEGER,
            StarParticleEffect::maxAge,
            PacketCodecs.FLOAT,
            StarParticleEffect::turnRate,
            PacketCodecs.FLOAT,
            StarParticleEffect::upwardsAx,
            StarParticleEffect::new
    );

    public ParticleType<?> getType() {
        return SignalParticleTypes.STAR;
    }
}
