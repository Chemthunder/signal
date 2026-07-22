package org.autumn.signal.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.SignalClient;
import org.autumn.signal.core.cca.entity.SignalComponent;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {
    @WrapMethod(method = "renderHealthBar")
    private void signal$noHearts(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, Operation<Void> original) {
        if (!SignalComponent.KEY.get(player).isClanker()) {
            original.call(context, player, x, y, lines, regeneratingHeartIndex, maxHealth, lastHealth, health, absorption, blinking);
        }
    }

    @WrapMethod(method = "renderFood")
    private void signal$noFood(DrawContext context, PlayerEntity player, int top, int right, Operation<Void> original) {
        if (!SignalComponent.KEY.get(player).isClanker()) {
            original.call(context, player, top, right);
        }
    }
}
