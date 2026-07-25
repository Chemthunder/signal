package org.autumn.signal.core.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import org.autumn.signal.core.SignalClient;
import org.autumn.signal.core.cca.world.DropshipComponent;
import org.autumn.signal.core.utilities.ConsoleFeedback;

/**
 * @author Chemthunder
 */
public class ConsoleEvent implements HudRenderCallback {
    public void onHudRender(DrawContext context, RenderTickCounter renderTickCounter) {
        float delta = renderTickCounter.getTickDelta(true);
        World world = MinecraftClient.getInstance().world;

        if (world == null) return;

        DropshipComponent d = DropshipComponent.KEY.get(world);

        int color = ColorHelper.Argb.withAlpha(d.getConsoleOpacity() * 2, 0xFFFFFFFF);

        if (d.getConsoleOpacity() > 5) {
            if (d.getTime() <= 55) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        ConsoleFeedback.DEPLOY_DROPSHIP.getFirst().text(),
                        20,
                        20,
                        color,
                        true
                );
            }

            if (d.getTime() <= 50) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        ConsoleFeedback.DEPLOY_DROPSHIP.get(1).text(),
                        20,
                        30,
                        color,
                        true
                );
            }

            if (d.getTime() <= 45) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        ConsoleFeedback.DEPLOY_DROPSHIP.get(2).text(),
                        20,
                        40,
                        color,
                        true
                );
            }

            if (d.getTime() <= 25) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        ConsoleFeedback.PAYLOAD_DEPLOYED.getFirst().text(),
                        20,
                        70,
                        color,
                        true
                );
            }

            if (d.getTime() <= 10) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        ConsoleFeedback.PAYLOAD_DEPLOYED.get(1).text(),
                        20,
                        80,
                        color,
                        true
                );
            }

            if (d.getTime() <= 5) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        "Extracting environmental data for reconnaissance.",
                        20,
                        100,
                        color,
                        true
                );
            }

            if (d.getTime() <= 2) {
                context.drawText(
                        MinecraftClient.getInstance().textRenderer,
                        "Safe travels, wanderer.",
                        20,
                        130,
                        color,
                        true
                );
            }
        }
    }
}
