package com.plattysoft.leonids.initializers;

import com.plattysoft.leonids.Particle;

import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/6/17.
 */

public class SpeedToCircleCenterInitializer extends SpeeddModuleAndRangeInitializer {

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

    private float mCenterX;
    private float mCenterY;

    public SpeedToCircleCenterInitializer(float speedMin, float speedMax, int minAngle, int maxAngle, float centerX, float centerY) {
        super(speedMin, speedMax, minAngle, maxAngle);

        mCenterX = centerX;
        mCenterY = centerY;
    }

    @Override
    public void initParticle(Particle p, Random r) {
        super.initParticle(p, r);

        int quadrant = getQuadrant(p);

        switch (quadrant) {
            case 1: {
                if (p.mSpeedX > 0) {
                    p.mSpeedX = -p.mSpeedX;
                }
                if (p.mSpeedY > 0) {
                    p.mSpeedY = -p.mSpeedY;
                }
            }
            break;
            case 2: {
                if (p.mSpeedX > 0) {
                    p.mSpeedX = -p.mSpeedX;
                }
                if (p.mSpeedY < 0) {
                    p.mSpeedY = -p.mSpeedY;
                }
            }
            break;
            case 3: {
                if (p.mSpeedX < 0) {
                    p.mSpeedX = -p.mSpeedX;
                }
                if (p.mSpeedY < 0) {
                    p.mSpeedY = -p.mSpeedY;
                }
            }
            break;
            case 4: {
                if (p.mSpeedX < 0) {
                    p.mSpeedX = -p.mSpeedX;
                }
                if (p.mSpeedY > 0) {
                    p.mSpeedY = -p.mSpeedY;
                }
            }
            break;
        }
    }

    private int getQuadrant(Particle p) {
        boolean isBottomQuadrants = p.mInitialX > mCenterX;
        boolean isRightQuadrants = p.mInitialY > mCenterY;

        return Quadrant.getQuadrant(isBottomQuadrants, isRightQuadrants).mQuadrantOrdinal;
    }
}
