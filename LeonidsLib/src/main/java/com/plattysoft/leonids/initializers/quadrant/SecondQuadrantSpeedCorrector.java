package com.plattysoft.leonids.initializers.quadrant;

import com.plattysoft.leonids.Particle;

/**
 * Created by artsiomkaliaha on 2/6/17.
 */

public class SecondQuadrantSpeedCorrector implements SpeedCorrector {

    private boolean mIsInsideEmission;

    public SecondQuadrantSpeedCorrector(boolean mIsInsideEmission) {
        this.mIsInsideEmission = mIsInsideEmission;
    }

    @Override
    public void apply(Particle particle) {
        if (mIsInsideEmission ? particle.mSpeedX > 0 : particle.mSpeedX < 0) {
            particle.mSpeedX = -particle.mSpeedX;
        }
        if (mIsInsideEmission ? particle.mSpeedY < 0 : particle.mSpeedY > 0) {
            particle.mSpeedY = -particle.mSpeedY;
        }
    }
}