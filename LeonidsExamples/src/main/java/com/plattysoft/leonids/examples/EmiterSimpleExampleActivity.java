package com.plattysoft.leonids.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;

import com.plattysoft.leonids.ParticleSystem;

public class EmiterSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 1000, R.drawable.star_pink, 700)
				.setSpeedRange(0.04f, 0.05f)
				.setAccelerationModuleAndAndAngleRange(0.00003f, 0.00007f, -180, 0)
				.setFadeIn(300, new LinearInterpolator())
				.setFadeOut(300, new LinearInterpolator())
				.oneCircularShot(arg0, 1000);
	}

}
