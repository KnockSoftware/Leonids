package com.plattysoft.leonids.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class EmiterSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		//particles moving from bottom to top
//		final ParticleSystem p = new ParticleSystem(this, 50, R.drawable.star_pink, 4000)
//				.setScaleRange(0.7f, 1.3f)
//				.setSpeedModuleAndAngleRange(0.008f, 0.016f, -90, -90)
//				.setAcceleration(0.000015f, -90)
//				.setFadeOut(300)
//				.setRandomPositionWithinView(arg0);
//
//		p.emit(arg0, 8);
//
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				p.stopEmitting();
//			}
//		}, 5000);

		//rectangular dust
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 700)
//				.setSpeedRange(0.04f, 0.05f)
//				.setAccelerationModuleAndAndAngleRange(0.00003f, 0.00007f, 0, 360)
//				.setFadeOut(300)
//				.oneRectangularShot(arg0, 1000);

		//circular dust
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 700)
//				.setSpeedRange(0.04f, 0.05f)
//				.setAccelerationModuleAndAndAngleRange(0.00003f, 0.00007f, -180, 0)
//				.setFadeOut(300)
//				.oneCircularShot(arg0, 1000);

		//infinity
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 1000)
//				.setFadeOut(300)
//				.setSpeedRange(0.02f, 0.025f)
//				.setCircularInitialPosition(arg0, 0.7f)
//				.setSpeedModuleToCircleCenterInitializer(arg0, 0.25f, 0.25f, false)
//				.emit(arg0, 200);

		//night sky
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 500)
//				.setFadeOut(300)
//				.setRandomPositionWithinView(arg0)
//				.emitFromRectangle(arg0, 100);

		//to the center of a circle
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 1900)
//				.setFadeOut(300)
//				.setCircularInitialPosition(arg0)
//				.setSpeedModuleToCircleCenterInitializer(arg0, 0.25f, 0.25f, true)
//				.setRotationSpeed(180)
//				.emit(arg0, 75);

		//extra effect chaos
//		new ParticleSystem(this, 1000, R.drawable.star_pink, 7000)
//				.setDelayedFadeIn(5000, 200)
//				.setFadeOut(300)
//				.setSpeedRange(0.02f, 0.025f)
//				.emitFromRectangle(arg0, 100);
	}
}