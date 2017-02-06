package com.plattysoft.leonids.initializers.quadrant;

import com.plattysoft.leonids.Particle;

/**
 * Created by artsiomkaliaha on 2/6/17.
 */

public class SecondQuadrantSpeedCorrector implements SpeedCorrector {
    @Override
    public void apply(Particle particle) {
        if (particle.mSpeedX > 0) {
            particle.mSpeedX = -particle.mSpeedX;
        }
        if (particle.mSpeedY < 0) {
            particle.mSpeedY = -particle.mSpeedY;
        }
    }
}