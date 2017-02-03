package com.plattysoft.leonids.initializers;

import android.graphics.Rect;

import com.plattysoft.leonids.Particle;

import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/3/17.
 */

public class RandomPositionInitializer implements ParticleInitializer {

    private Rect mBounds;

    public RandomPositionInitializer(Rect mBounds) {
        this.mBounds = mBounds;
    }

    @Override
    public void initParticle(Particle p, Random r) {
        int mInitialX = mBounds.left + r.nextInt(mBounds.right - mBounds.left);
        int mInitialY = mBounds.top + r.nextInt(mBounds.bottom - mBounds.top);
        p.setInitialPosition(mInitialX, mInitialY);
    }
}
