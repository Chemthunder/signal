package org.autumn.signal.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.autumn.signal.core.index.SignalItems;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class SignalLanguageProvider extends FabricLanguageProvider {
    public SignalLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        SignalItems.rant.registerLang(wrapperLookup, translationBuilder);
    }
}
