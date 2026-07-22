package org.autumn.signal.core.cca;

import org.autumn.signal.core.cca.entity.SignalComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author Chemthunder
 */
public class SignalCCA implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry module) {
        module.registerForPlayers(SignalComponent.KEY, SignalComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
