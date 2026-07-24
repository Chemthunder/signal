package org.autumn.signal.core.index;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.Signal;

/**
 * @author Chemthunder
 */
public interface SignalRegistries {
    RegistryKey<Registry<SignalFrequency>> frequencyKey = RegistryKey.ofRegistry(Signal.id("signal_frequency"));
    Registry<SignalFrequency> SIGNAL_FREQUENCY = FabricRegistryBuilder.createSimple(frequencyKey)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {}
}
