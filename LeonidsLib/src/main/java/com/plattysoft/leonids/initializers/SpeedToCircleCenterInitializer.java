package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.initializers.quadrant.FirstQuadrantSpeedCorrector;
import com.plattysoft.leonids.initializers.quadrant.ForthQuadrantSpeedCorrector;
import com.plattysoft.leonids.initializers.quadrant.SecondQuadrantSpeedCorrector;
import com.plattysoft.leonids.initializers.quadrant.SpeedCorrector;
import com.plattysoft.leonids.initializers.quadrant.ThirdQuadrantSpeedCorrector;
import com.plattysoft.leonids.utils.RandomUtils;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/6/17.
 */

public class SpeedToCircleCenterInitializer implements ParticleInitializer {

    private enum Quadrant {

        FIRST(1),
        SECOND(2),
        THIRD(3),
        FORTH(4);

        public int mQuadrantOrdinal;

        private boolean mIsBottomQuadrant;
        private boolean mIsRightQuadrant;

        Quadrant(int quadrant) {
            mQuadrantOrdinal = quadrant;

            switch (quadrant) {
                case 1: {
                    mIsBottomQuadrant = true;
                    mIsRightQuadrant = true;
                }
                break;
                case 2: {
                    mIsBottomQuadrant = true;
                    mIsRightQuadrant = false;
                }
                break;
                case 3: {
                    mIsBottomQuadrant = false;
                    mIsRightQuadrant = false;
                }
                break;
                case 4: {
                    mIsBottomQuadrant = false;
                    mIsRightQuadrant = true;
                }
                break;
            }
        }

        public static Quadrant getQuadrant(boolean isBottom, boolean isRight) {
            for (Quadrant quadrant : Quadrant.values()) {
                if (isBottom == quadrant.mIsBottomQuadrant && isRight == quadrant.mIsRightQuadrant) {
                    return quadrant;
                }
            }

            return Quadrant.FIRST;
        }
    }

    private final float mCenterX;
    private final float mCenterY;
    private final float mSpeed;

    private final float[] mXAxisVector;

    private HashMap<Integer, SpeedCorrector> SpeedCorrectors = new HashMap<Integer, SpeedCorrector>() {{
        put(1, new FirstQuadrantSpeedCorrector());
        put(2, new SecondQuadrantSpeedCorrector());
        put(3, new ThirdQuadrantSpeedCorrector());
        put(4, new ForthQuadrantSpeedCorrector());
    }};

    public SpeedToCircleCenterInitializer(float speedMin, float speedMax, float centerX, float centerY) {
        mSpeed = RandomUtils.nextFloat(speedMin, speedMax);

        mCenterX = centerX;
        mCenterY = centerY;

        //vector aligned with x axis
        mXAxisVector = new float[2];
        mXAxisVector[0] = 2;
        mXAxisVector[1] = 0;
    }

    @Override
    public void initParticle(Particle particle, Random r) {
        float pureParticleX = particle.mInitialX - mCenterX;
        float pureParticleY = particle.mInitialY - mCenterY;

        float scalarMultiplicationOfVectors = pureParticleX * mXAxisVector[0] + pureParticleY * mXAxisVector[1];
        float particleVectorModule = (float)Math.sqrt(pureParticleX * pureParticleX + pureParticleY * pureParticleY);
        float xAxisVectorModule = (float)Math.sqrt(mXAxisVector[0] * mXAxisVector[0] + mXAxisVector[1] * mXAxisVector[1]);

        double rawAngleRad = Math.acos(scalarMultiplicationOfVectors / (particleVectorModule * xAxisVectorModule));
//        int rawAngleDegrees = 360 - (int)((180 * rawAngleRad) / Math.PI);
//        float angleInRads = (float) (rawAngleDegrees * Math.PI/180f);

        particle.mSpeedX = (float) (mSpeed * Math.cos(rawAngleRad));
        particle.mSpeedY = (float) (mSpeed * Math.sin(rawAngleRad));

        SpeedCorrectors
                .get(getQuadrant(particle))
                .apply(particle);
    }

    private int getQuadrant(Particle p) {
        boolean isBottomQuadrants = p.mInitialX > mCenterX;
        boolean isRightQuadrants = p.mInitialY > mCenterY;

        return Quadrant.getQuadrant(isBottomQuadrants, isRightQuadrants).mQuadrantOrdinal;
    }
}
