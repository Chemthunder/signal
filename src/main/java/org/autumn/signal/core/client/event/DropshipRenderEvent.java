package org.autumn.signal.core.client.event;

import foundry.veil.api.client.util.Easing;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.autumn.signal.api.client.render.Nitro;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.SignalClient;
import org.autumn.signal.core.cca.world.DropshipComponent;

/**
 * @author Chemthunder
 */
public class DropshipRenderEvent implements WorldRenderEvents.Last {
    public void onLast(WorldRenderContext worldRenderContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        RenderTickCounter renderTickCounter = worldRenderContext.tickCounter();
        Camera camera = worldRenderContext.camera();
        MatrixStack stack = worldRenderContext.matrixStack();

        float delta = renderTickCounter.getTickDelta(true);

        if (stack != null) {
            VertexConsumerProvider immediate = worldRenderContext.consumers();

            if (immediate != null) {
                if (client.world != null) {
                    DropshipComponent d = DropshipComponent.KEY.get(client.world);

                    float size = d.getDropshipScale();

                    Vec3d pos = d.getPos();

                    if (pos != null) {
                        float x = (float) (pos.x - camera.getPos().x);
                        float y1 = (float) ((pos.y) - camera.getPos().y);
                        float y2 = (float) ((pos.y + 100) - camera.getPos().y);

                        float z = (float) (pos.z - camera.getPos().z);

                        if (d.getBeamWidth() > 0) {
                            createDeployMarker(d, stack, immediate, x, y1, z, size, delta);
                        }

                        if (d.getTime() == 0) {
                            createDropship(d, stack, immediate, x, y2, z, size, delta);
                        }
                    }
                }
            }
        }
    }

    public static void createDeployMarker(DropshipComponent d, MatrixStack stack, VertexConsumerProvider immediate, float x, float y, float z, float size, float delta) {
        stack.push();

        Nitro.skyBeam(
                stack,
                immediate.getBuffer(RenderLayer.getEntityTranslucent(Signal.id("textures/render/color.png"))),
                x,
                y,
                z,
                Easing.EASE_OUT_EXPO.ease(d.getBeamWidth()),
                360,
                0
        );

        stack.pop();
    }

    public static void createDropship(DropshipComponent d, MatrixStack stack, VertexConsumerProvider immediate, float x, float y, float z, float size, float delta) {
        stack.push();

        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180), x, y, z);

        stack.translate(x, y, z);

        {
            stack.push();

            stack.scale(size, size, size);

            stack.translate(-((float) 5 / 2), 0, -((float) 5 / 2));

            Nitro.quad(
                    stack.peek(),
                    immediate.getBuffer(RenderLayer.getEyes(Signal.id("textures/render/color.png"))),
                    10,
                    0,
                    5,

                    5,
                    0,
                    0,

                    0,
                    0,
                    0,

                    5,
                    0,
                    5,

                    1,
                    1,
                    1,
                    1
            );

            stack.pop();
        }

        {
            stack.push();

            stack.scale(size - 0.5F, size - 0.5F, size - 0.5F);

            stack.translate(-((float) 5 / 2), 0, -((float) 5 / 2));

            stack.translate(0.05F, 0, 0);

            Nitro.quad(
                    stack.peek(),
                    immediate.getBuffer(RenderLayer.getEndGateway()),
                    10,
                    0,
                    5,

                    5,
                    0,
                    0,

                    0,
                    0,
                    0,

                    5,
                    0,
                    5,

                    -1,
                    -1,
                    -1,
                    -1
            );

            stack.pop();
        }

        stack.pop();
    }

    public static void createOldDropship(DropshipComponent d, MatrixStack stack, VertexConsumerProvider immediate, float x, float y, float z, float size, float delta) {
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
                    immediate.getBuffer(RenderLayer.getBeaconBeam(Signal.id("textures/render/color.png"), true)),
                    x,
                    y,
                    z,
                    size + 2,
                    new Vec2f(0, 0),
                    1
            );

            stack.pop();
        }

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
                    immediate.getBuffer(RenderLayer.getEntityTranslucent(Signal.id("textures/render/signals.png"), true)),
                    x,
                    y,
                    z,
                    size + 2.5F,
                    new Vec2f((SignalClient.GLOBAL_AGE + delta) / 32, 0),
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

            Nitro.texCube(
                    stack,
                    immediate.getBuffer(RenderLayer.getEndGateway()),
                    x,
                    y,
                    z,
                    -size,
                    new Vec2f(0, 0),
                    1
            );

            stack.pop();
        }

        if (d.isPresent()) {
            stack.push();

            stack.translate(x, y, z);

            stack.multiply(
                    RotationAxis.POSITIVE_X.rotation(25),
                    x,
                    y,
                    z
            );

            stack.multiply(
                    RotationAxis.POSITIVE_Y.rotation((SignalClient.GLOBAL_AGE + delta) / 8),
                    x,
                    y,
                    z
            );

            {
                stack.push();

                stack.translate(x, y, z);

                stack.scale(1, 0.01F, 1);

                Nitro.texSphere(
                        stack,
                        immediate.getBuffer(RenderLayer.getEntityTranslucentCull(Signal.id("textures/render/ring.png"))),
                        0,
                        0,
                        0,
                        9,
                        50,
                        0
                );

                stack.pop();
            }

            {
                stack.push();

                Nitro.texSphere(
                        stack,
                        immediate.getBuffer(RenderLayer.getEntityTranslucentEmissive(Signal.id("textures/render/color.png"))),
                        x,
                        y,
                        z,
                        5.5F,
                        50,
                        0
                );

                stack.pop();
            }

            {
                stack.push();

                Nitro.texSphere(
                        stack,
                        immediate.getBuffer(RenderLayer.getEntityTranslucent(Signal.id("textures/render/colors/black.png"))),
                        x,
                        y,
                        z,
                        5,
                        50,
                        0
                );

                stack.pop();
            }

            stack.pop();
        }
    }
}
