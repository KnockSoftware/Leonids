package com.plattysoft.leonids.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
		new ParticleSystem(this, 1000, R.drawable.star_pink, 1900)
				.setFadeOut(300)
				.setCircularInitialPosition(arg0)
				.setSpeedModuleToCircleCenterInitializer(arg0, 0.25f, 0.25f)
				.setRotationSpeed(180)
				.emit(arg0, 75);
	}
}