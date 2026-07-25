package org.autumn.signal.core.frequency;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.cca.world.DropshipComponent;

/**
 * @author Chemthunder
 */
public class DropshipDeployFrequency extends SignalFrequency {
    public DropshipDeployFrequency() {
        super(Text.literal("Dropship Deployment"));
    }

    public void use(World world, PlayerEntity player, BlockPos pos, ItemStack stack) {

    }
}
