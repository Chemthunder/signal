package org.autumn.signal.core.event.client;

import foundry.veil.api.client.render.VeilRenderer;
import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.api.event.VeilRendererAvailableEvent;
import foundry.veil.platform.VeilEventPlatform;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.RotationAxis;
import org.autumn.signal.api.client.render.Nitro;
import org.autumn.signal.core.Signal;
import org.autumn.signal.core.SignalClient;

/**
 * @author Chemthunder
 */
public class DropshipRenderEvent {
    public static void create() {
        VeilEventPlatform.INSTANCE.onVeilRenderLevelStage(((
                stage,
                worldRenderer,
                immediate,
                matrixStack,
                matrix4fc,
                matrix4fc1,
                i,
                renderTickCounter,
                camera,
                frustum
        ) -> {
            if (stage.equals(VeilRenderLevelStageEvent.Stage.AFTER_SKY)) {
                MatrixStack stack = matrixStack.toPoseStack();

                float x = (float) (100 - camera.getPos().x);
                float y = (float) (100 - camera.getPos().y);
                float z = (float) (100 - camera.getPos().z);

                float delta = renderTickCounter.getTickDelta(true);

                stack.push();

                stack.translate(x, y, z);

                stack.multiply(
                        RotationAxis.POSITIVE_Y.rotationDegrees((SignalClient.GLOBAL_AGE + delta) / 4),
                        x,
                        y,
                        z
                );

                Nitro.solColCube(
                        stack,
                        immediate.getBuffer(RenderLayer.getEndPortal()),
                        0xFFffffff,
                        x,
                        y,
                        z,
                        20
                );

                stack.pop();
            }
        }));
    }
}
