package org.autumn.signal.core.index;

import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.client.particle.StarParticle;
import org.autumn.signal.core.client.particle.StarParticleEffect;

/**
 * @author Chemthunder
 */
public interface SignalParticleTypes {
    ParticleTypeRegistrant rant = new ParticleTypeRegistrant(Signal.MOD_ID);

    ParticleType<StarParticleEffect> STAR = rant.register("star", FabricParticleTypes.complex(true, StarParticleEffect.CODEC, StarParticleEffect.PACKET_CODEC));

    static void init() {}

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(STAR, StarParticle.Factory::new);
    }
}
