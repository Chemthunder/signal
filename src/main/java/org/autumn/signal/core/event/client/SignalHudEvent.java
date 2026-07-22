package org.autumn.signal.core.event.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.autumn.signal.core.cca.entity.SignalComponent;

/**
 * @author Chemthunder
 */
public class SignalHudEvent implements HudRenderCallback {
    public void onHudRender(DrawContext context, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        SignalComponent signal = SignalComponent.KEY.get(player);

        if (signal.isClanker()) {
            context.drawCenteredTextWithShadow(
                    client.textRenderer,
                    Text.literal("Test"),
                    context.getScaledWindowWidth() / 2,
                    context.getScaledWindowHeight() / 2,
                    0xFFffffff
            );

            context.drawText(
                    client.textRenderer,
                    Text.literal(Math.round(player.getHealth()) + "/" + Math.round(player.getMaxHealth())),
                    5,
                    10,
                    0xFFffffff,
                    true
            );

            context.drawText(
                    client.textRenderer,
                    Text.literal(player.getHungerManager().getFoodLevel() + ""),
                    5,
                    20,
                    0xFFffffff,
                    true
            );

            context.drawText(
                    client.textRenderer,
                    Text.literal(player.getWorld().getLunarTime() + ""),
                    5,
                    30,
                    0xFFffffff,
                    true
            );
        }
    }
}
