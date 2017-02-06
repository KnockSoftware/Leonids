package com.plattysoft.leonids.modifiers;


import com.plattysoft.leonids.Particle;

import java.util.Random;

/**
 * Created by artsiomkaliaha on 2/3/17.
 */

public class AccelerationToCircleCenterModifier implements ParticleModifier {

    private float mCircleCenterX;
    private float mCircleCenterY;
    private float mMillisecondsToCenter;
    private double mSpeed;

    private int mMinAngle;
    private int mMaxAngle;

    private float mVelocityX;
    private float mVelocityY;
    private float mAngleInRads;

    private boolean mIsInitialSetUp = true;

    public AccelerationToCircleCenterModifier(float circleCenterX, float circleCenterY, float millisecondsToCenter, int minAngle, int maxAngle) {
        this.mCircleCenterX = circleCenterX;
        this.mCircleCenterY = circleCenterY;
        this.mMillisecondsToCenter = millisecondsToCenter;

        mMinAngle = minAngle;
        mMaxAngle = maxAngle;
        // Make sure the angles are in the [0-360) range
        while (mMinAngle < 0) {
            mMinAngle+=360;
        }
        while (mMaxAngle < 0) {
            mMaxAngle+=360;
        }
        // Also make sure that mMinAngle is the smaller
        if (mMinAngle > mMaxAngle) {
            int tmp = mMinAngle;
            mMinAngle = mMaxAngle;
            mMaxAngle = tmp;
        }

        mAngleInRads = minAngle * 0.0174533f;
    }

    @Override
    public void apply(Particle particle, long miliseconds) {
        if (mIsInitialSetUp && particle.mInitialX != 0 && particle.mInitialY != 0) {
            double vectorLength = Math.sqrt(particle.mInitialX * particle.mInitialX + particle.mInitialY * particle.mInitialY);
            mSpeed = vectorLength / mMillisecondsToCenter / 1000;

            mVelocityX = (float)(mSpeed * Math.cos(mAngleInRads));
            mVelocityY = (float)(mSpeed * Math.sin(mAngleInRads));




            mIsInitialSetUp = false;
        }
//        particle.mCurrentX += mSpeed * miliseconds * miliseconds;
//        particle.mCurrentY += mSpeed * miliseconds * miliseconds;



        int angle;
        if (mMaxAngle == mMinAngle) {
            angle = mMinAngle;
        }
        else {
            angle = new Random().nextInt(mMaxAngle - mMinAngle) + mMinAngle;
        }
        float angleInRads = (float) (angle*Math.PI/180f);
        particle.mCurrentX += mVelocityX * miliseconds * miliseconds;
        particle.mCurrentY += mVelocityY * miliseconds * miliseconds;
    }
}
