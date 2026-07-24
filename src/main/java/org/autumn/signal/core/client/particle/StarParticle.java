package org.autumn.signal.core.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

/**
 * @author Chemthunder
 */
public class StarParticle extends AnimatedParticle {
    private final SpriteProvider spriteWithAge;
    private final float turnRate;

    private final boolean turnDir;

    public StarParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteWithAge, StarParticleEffect effect) {
        super(world, x, y, z, spriteWithAge, effect.upwardsAx());

        Random random = Random.create();

        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;

        this.turnRate = effect.turnRate();

        this.maxAge = effect.maxAge();

        this.scale = effect.size();

        this.spriteWithAge = spriteWithAge;
        this.setSprite(spriteWithAge.getSprite(random));

        turnDir = random.nextBetween(0, 1) != 0;
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    public void method_60373(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float f) {
        super.method_60373(vertexConsumer, camera, quaternionf, f);
    }

    public void tick() {
        super.tick();

        this.prevAngle = this.angle;
        this.angle += this.turnDir ? this.turnRate : -this.turnRate;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<StarParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        public Particle createParticle(StarParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new StarParticle(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider, parameters);
        }
    }
}
