package org.autumn.signal.core.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.autumn.signal.api.item.SignalFrequency;
import org.autumn.signal.core.cca.world.DropshipComponent;
import org.autumn.signal.core.index.SignalComponentTypes;
import org.autumn.signal.core.networking.s2c.FlashPayload;

/**
 * @author Chemthunder
 */
public class LeylineItem extends Item {
    public LeylineItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player != null) {
            if (player.isSneaking()) {
                DropshipComponent d = DropshipComponent.KEY.get(world);

                d.start(new Vec3d(
                        pos.getX(),
                        pos.getY(),
                        pos.getZ()
                ));

                player.swingHand(player.getActiveHand());

                world.playSound(null, pos, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1, 1);
                world.playSound(null, pos, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1, 1);
                world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1, 1);
                world.playSound(null, pos, SoundEvents.ENTITY_IRON_GOLEM_STEP, SoundCategory.BLOCKS, 1, 1);
            }
        }
        return super.useOnBlock(context);
    }
}
