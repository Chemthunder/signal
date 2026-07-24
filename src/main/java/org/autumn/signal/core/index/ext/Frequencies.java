package org.autumn.signal.core.index.ext;

import net.minecraft.registry.Registry;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.frequency.DropshipDeployFrequency;
import org.autumn.signal.core.index.SignalRegistries;

/**
 * @author Chemthunder
 */
public interface Frequencies {
    SignalFrequency DROPSHIP = create("dropship", new DropshipDeployFrequency());

    private static SignalFrequency create(String name, SignalFrequency frequency) {
        return Registry.register(SignalRegistries.SIGNAL_FREQUENCY, Signal.id(name), frequency);
    }
}
