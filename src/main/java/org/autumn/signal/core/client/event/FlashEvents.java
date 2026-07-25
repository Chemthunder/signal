package org.autumn.signal.core.client.event;

import com.mojang.blaze3d.systems.RenderSystem;
import foundry.veil.api.client.util.Easing;
import net.acoyt.acornlib.api.event.RenderOverlayEvent;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.autumn.signal.core.Signal;

import java.util.Optional;

/**
 * @author Chemthunder
 */
public class FlashEvents {
    public static final class Ticker implements ClientTickEvents.EndTick {
        public static float opacity = 0.0F;

        public void onEndTick(MinecraftClient client) {
            opacity = Math.clamp(opacity, 0.0F, 1.0F);

            if (!client.isPaused()) {
                if (opacity > 0.0F) {
                    opacity -= 0.01F;
                }
            }
        }
    }

    public static final class Renderer implements HudRenderCallback {
        public void onHudRender(DrawContext context, RenderTickCounter renderTickCounter) {
            float o = Ticker.opacity;

            if (o > 0.0F) {
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();

                context.setShaderColor(
                        1.0F,
                        1.0F,
                        1.0F,
                        o == 1 ? o : (float) (o - Math.pow(2, -10 * o))
                );

                context.drawTexture(
                        Signal.id("textures/render/color.png"),
                        0,
                        0,
                        -90,
                        0.0F,
                        0.0F,
                        context.getScaledWindowWidth(),
                        context.getScaledWindowHeight(),
                        context.getScaledWindowWidth(),
                        context.getScaledWindowHeight()
                );

                RenderSystem.disableBlend();
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();

                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
