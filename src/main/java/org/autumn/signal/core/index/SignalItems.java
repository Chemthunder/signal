package org.autumn.signal.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.index.ext.Frequencies;
import org.autumn.signal.core.item.BeaconItem;

/**
 * @author Chemthunder
 */
public interface SignalItems {
    ItemRegistrant rant = new ItemRegistrant(Signal.MOD_ID);

    Item BEACON = rant.register("beacon", BeaconItem::new, new Item.Settings()
            .maxCount(1)
            .fireproof()
            .component(SignalComponentTypes.STORED_FREQUENCY, Frequencies.DROPSHIP)
    );

    static void init() {}
}
