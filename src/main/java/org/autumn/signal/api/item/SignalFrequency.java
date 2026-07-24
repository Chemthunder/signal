package org.autumn.signal.api.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.acoyt.acornlib.api.util.PortingUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class SignalFrequency {
    private final Text display;

    public SignalFrequency(Text display) {
        this.display = display;
    }

    public void use(World world, PlayerEntity user, BlockPos pos, ItemStack stack) {}

    public Text getDisplay() {
        return display;
    }

    public static final Codec<SignalFrequency> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TextCodecs.CODEC.optionalFieldOf("display", Text.empty()).forGetter(SignalFrequency::getDisplay)
    ).apply(instance, SignalFrequency::new));

    public static final PacketCodec<ByteBuf, SignalFrequency> PACKET = PacketCodecs.codec(CODEC);
}
