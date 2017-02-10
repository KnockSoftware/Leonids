package com.plattysoft.leonids.modifiers;

import com.plattysoft.leonids.Particle;

/**
 * Created by artsiomkaliaha on 2/10/17.
 */

public class CircleScaleAndAlphaModifier implements ParticleModifier {

    private float mInitialDistanceToCenter;
    private final float mCircleCenterX;
    private final float mCircleCenterY;

    private boolean mIsDistanceToCenterInitialized;

    public CircleScaleAndAlphaModifier(float mCircleCenterX, float mCircleCenterY) {
        this.mCircleCenterX = mCircleCenterX;
        this.mCircleCenterY = mCircleCenterY;
    }

    @Override
    public void apply(Particle particle, long miliseconds) {
        if (!mIsDistanceToCenterInitialized) {
            mInitialDistanceToCenter = getDistanceToCenter(particle) - particle.getBitmapHalfHeight();
            mIsDistanceToCenterInitialized = true;
            return;
        }

        float distanceFromParticleToCenter = getDistanceToCenter(particle);
        float percentsToCenter = distanceFromParticleToCenter / mInitialDistanceToCenter;

        particle.mAlpha = (int)(percentsToCenter * 255);
        particle.mScale = percentsToCenter;

        if (particle.mAlpha <= 255 * 0.1) {
            particle.destroy();
        }
    }

    private float getDistanceToCenter(Particle particle) {
        return (float)Math.sqrt(Math.pow(particle.mCurrentX - mCircleCenterX, 2) + Math.pow(particle.mCurrentY - mCircleCenterY, 2));
    }
}
