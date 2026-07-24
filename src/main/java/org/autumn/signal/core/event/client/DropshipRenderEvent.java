package org.autumn.signal.core.event.client;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec2f;
import org.autumn.signal.api.client.render.Nitro;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.SignalClient;

/**
 * @author Chemthunder
 */
public class DropshipRenderEvent implements WorldRenderEvents.Last {
    public void onLast(WorldRenderContext worldRenderContext) {
        RenderTickCounter renderTickCounter = worldRenderContext.tickCounter();
        Camera camera = worldRenderContext.camera();
        MatrixStack stack = worldRenderContext.matrixStack();

        float x = (float) (100 - camera.getPos().x);
        float y = (float) (90 - camera.getPos().y);
        float z = (float) (100 - camera.getPos().z);

        float delta = renderTickCounter.getTickDelta(true);

        float size = 50;

        if (stack != null) {
            VertexConsumerProvider immediate = worldRenderContext.consumers();

            if (immediate != null) {
                {
                    stack.push();

                    stack.multiply(
                            RotationAxis.POSITIVE_Y.rotationDegrees((SignalClient.GLOBAL_AGE + delta) / 4F),
                            x,
                            y,
                            z
                    );

                    stack.multiply(
                            RotationAxis.POSITIVE_Z.rotationDegrees((SignalClient.GLOBAL_AGE + delta) / 4F),
                            x,
                            y,
                            z
                    );

                    Nitro.texCube(
                            stack,
                            immediate.getBuffer(RenderLayer.getBeaconBeam(Signal.id("icon.png"), true)),
                            x,
                            y,
                            z,
                            size + 3,
                            new Vec2f(0, 0),
                            1
                    );

                    stack.pop();
                }

                {
                    stack.push();

                    stack.multiply(
                            RotationAxis.POSITIVE_Y.rotationDegrees((SignalClient.GLOBAL_AGE + delta) / 4),
                            x,
                            y,
                            z
                    );

                    stack.multiply(
                            RotationAxis.POSITIVE_Z.rotationDegrees((SignalClient.GLOBAL_AGE + delta) / 4),
                            x,
                            y,
                            z
                    );

                    Nitro.texCube(
                            stack,
                            immediate.getBuffer(RenderLayer.getEndGateway()),
                            x,
                            y,
                            z,
                            size,
                            new Vec2f(0, 0),
                            1
                    );

                    stack.pop();
                }
            }
        }
    }
}
