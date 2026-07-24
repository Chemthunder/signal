package org.autumn.signal.core.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.cca.world.DropshipComponent;
import org.autumn.signal.core.index.SignalComponentTypes;

/**
 * @author Chemthunder
 */
public class BeaconItem extends Item {
    public BeaconItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        ItemStack stack = context.getStack();

        if (player != null) {
            if (player.isSneaking()) {
                if (stack.get(SignalComponentTypes.STORED_FREQUENCY) != null) {
                    SignalFrequency frequency = stack.get(SignalComponentTypes.STORED_FREQUENCY);

                    if (frequency != null) {
                        frequency.use(world, player, pos, stack);
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }
}
