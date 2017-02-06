package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;

import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/3/17.
 */

public class CircularInitializer implements ParticleInitializer {

    private int mRadius;
    private int mCenterX;
    private int mCenterY;

    public CircularInitializer(int mRadius, int mCenterX, int mCenterY) {
        this.mRadius = mRadius;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
    }

    @Override
    public void initParticle(Particle p, Random r) {
        float angle = r.nextInt(360) * 0.0174533f;
        int initialX = (int)(mCenterX + mRadius * Math.cos(angle));
        int initialY = (int)(mCenterY + mRadius * Math.sin(angle));
        p.setInitialPosition(initialX, initialY);
    }
}
