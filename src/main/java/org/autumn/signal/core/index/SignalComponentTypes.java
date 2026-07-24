package org.autumn.signal.core.index;

import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.Signal;

/**
 * @author Chemthunder
 */
public interface SignalComponentTypes {
    ComponentTypeRegistrant rant = new ComponentTypeRegistrant(Signal.MOD_ID);

    ComponentType<SignalFrequency> STORED_FREQUENCY = rant.register(
            "stored_frequency",
            SignalFrequency.CODEC,
            SignalFrequency.PACKET
    ); // applies to Beacons for different effects

    static void init() {}
}
