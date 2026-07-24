package org.autumn.signal.core.index;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.client.model.PlaneModel;

/**
 * @author Chemthunder
 */
public interface SignalModelLayers {
    EntityModelLayer PLANE = create("plane");

    private static EntityModelLayer create(String name) {
        return new EntityModelLayer(Signal.id(name), "main");
    }

    static void clientInit() {
        EntityModelLayerRegistry.registerModelLayer(PLANE, PlaneModel::getTexturedModelData);
    }
}
