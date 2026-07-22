package org.autumn.signal.core.cca.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.autumn.signal.core.Signal;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class SignalComponent implements AutoSyncedComponent {
    public static final ComponentKey<SignalComponent> KEY = ComponentRegistry.getOrCreate(
            Signal.id(Signal.MOD_ID),
            SignalComponent.class
    );
    private final PlayerEntity player;

    private boolean clanker = false;

    public SignalComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        clanker = nbt.getBoolean("Clanker");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("Clanker", clanker);
    }

    public boolean isClanker() {
        return clanker;
    }

    public void setClanker(boolean clanker) {
        this.clanker = clanker;
        sync();
    }
}
