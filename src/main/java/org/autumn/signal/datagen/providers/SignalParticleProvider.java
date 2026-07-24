package org.autumn.signal.datagen.providers;

import net.acoyt.acornlib.data.provider.resources.AcornParticleProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.index.SignalParticleTypes;

/**
 * @author Chemthunder
 */
public class SignalParticleProvider extends AcornParticleProvider {
    public SignalParticleProvider(FabricDataOutput output) {
        super(output);
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(SignalParticleTypes.STAR, rangeBetween(Signal.id("star"), 0, 0));
    }
}
