package org.autumn.signal.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.autumn.signal.datagen.providers.SignalLanguageProvider;
import org.autumn.signal.datagen.providers.SignalModelProvider;
import org.autumn.signal.datagen.providers.SignalParticleProvider;

/**
 * @author Chemthunder
 */
public class SignalDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
        var pack = fdg.createPack();

        pack.addProvider(SignalParticleProvider::new);
        pack.addProvider(SignalModelProvider::new);
        pack.addProvider(SignalLanguageProvider::new);
	}
}
