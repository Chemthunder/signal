package org.autumn.signal.core.cca.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.autumn.signal.core.Signal;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class DropshipComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<DropshipComponent> KEY = ComponentRegistry.getOrCreate(
            Signal.id("dropship"),
            DropshipComponent.class
    );
    private final World world;

    public static final float MAX_SCALE = 100.0F;

    private int time = 0;
    private int consoleOpacity = 1000;

    private @Nullable Vec3d pos = null;

    private float dropshipScale = 0.0F;
    private float beamWidth = 0.0F;

    private boolean present = false;
    private boolean primed = false;

    public DropshipComponent(World world) {
        this.world = world;
    }

    public void tick() {
        if (primed && pos != null) {
            consoleOpacity = Math.clamp(consoleOpacity, 0, 100);

            if (time > 0) {
                time--;
            } else {
                if (dropshipScale < MAX_SCALE) {
                    dropshipScale += 0.05F;
                } else {
                    primed = false;
                    present = true;
                }

                if (consoleOpacity > 0) {
                    consoleOpacity--;
                }
            }

            beamWidth = Math.clamp(beamWidth, 0.0F, 1F);

            if (time > 0) {
                if (beamWidth < 1F) {
                    beamWidth += 0.01F;
                }
            } else {
                if (beamWidth > 0.0F) {
                    beamWidth -= 0.01F;
                }
            }

            sync();
        }
    }

    public void start(Vec3d vec3d) {
        primed = true;
        dropshipScale = 0.5F;
        present = false;
        time = 60;
        pos = vec3d;
        consoleOpacity = 1000;
        beamWidth = 0.0F;

        sync();
    }

    public void sync() {
        KEY.sync(world);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        primed = nbt.getBoolean("Primed");
        dropshipScale = nbt.getFloat("DropshipScale");
        present = nbt.getBoolean("Present");
        time = nbt.getInt("Time");
        consoleOpacity = nbt.getInt("ConsoleOpacity");
        beamWidth = nbt.getFloat("BeamWidth");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("Primed", primed);
        nbt.putFloat("DropshipScale", dropshipScale);
        nbt.putBoolean("Present", present);
        nbt.putInt("Time", time);
        nbt.putInt("ConsoleOpacity", consoleOpacity);
        nbt.putFloat("BeamWidth", beamWidth);
    }

    public boolean isPrimed() {
        return primed;
    }

    public void setPrimed(boolean primed) {
        this.primed = primed;
        sync();
    }

    public float getDropshipScale() {
        return dropshipScale;
    }

    public void setDropshipScale(float dropshipScale) {
        this.dropshipScale = dropshipScale;
        sync();
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
        sync();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        sync();
    }

    public @Nullable Vec3d getPos() {
        return pos;
    }

    public void setPos(@Nullable Vec3d pos) {
        this.pos = pos;
        sync();
    }

    public int getConsoleOpacity() {
        return consoleOpacity;
    }

    public void setConsoleOpacity(int consoleOpacity) {
        this.consoleOpacity = consoleOpacity;
        sync();
    }

    public float getBeamWidth() {
        return beamWidth;
    }

    public void setBeamWidth(float beamWidth) {
        this.beamWidth = beamWidth;
        sync();
    }
}
