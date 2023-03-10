package net.venturecraft.gliders.client.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MovingSound extends AbstractTickableSoundInstance {

    private final Object entity;
    private final Supplier<Boolean> stopCondition;
    private boolean donePlaying = false;

    public MovingSound(Object object, SoundEvent soundIn, SoundSource categoryIn, boolean repeat, Supplier<Boolean> stopCondition, float volumeSfx, RandomSource randomSource) {
        super(soundIn, categoryIn, randomSource);
        this.entity = object;
        this.stopCondition = stopCondition;
        super.looping = repeat;
        volume = volumeSfx;
    }

    @Override
    public void tick() {

        if (entity instanceof Entity entityObject) {
            if (stopCondition.get() || !entityObject.isAlive()) {
                setDonePlaying();
            }

            super.x = (float) entityObject.getX();
            super.y = (float) entityObject.getY();
            super.z = (float) entityObject.getZ();
        }

        if (entity instanceof BlockEntity tileObject) {
            BlockPos pos = tileObject.getBlockPos();
            super.x = (float) pos.getX();
            super.y = (float) pos.getY();
            super.z = (float) pos.getZ();
        }

    }

    public void setDonePlaying() {
        this.looping = false;
        this.donePlaying = true;
        this.delay = 0;
    }

    @Override
    public boolean isLooping() {
        return this.looping;
    }

    @Override
    public float getVolume() {
        return this.volume;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public boolean isStopped() {
        return donePlaying;
    }

    @Override
    public int getDelay() {
        return this.delay;
    }

    @Override
    public @NotNull Attenuation getAttenuation() {
        return Attenuation.LINEAR;
    }
}