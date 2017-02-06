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
		new ParticleSystem(this, 1000, R.drawable.star_pink, 9000)
//				.setFadeOut(300)
				.setCircularInitialPosition(arg0)
				.setSpeedModuleToCircleCenterInitializer(arg0)
//				.setSpeedModuleAndAngleRange(0.005f, 0.006f, 0, 360)
//				.setSpeedRange()
				.emit(arg0, 50);
	}
}