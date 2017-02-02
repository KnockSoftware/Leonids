package com.plattysoft.leonids.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;

public class EmiterIntermediateExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		ParticleSystem ps = new ParticleSystem(this, 50, R.drawable.star_pink, 4000);
		ps.setScaleRange(0.7f, 1.3f);
		ps.setSpeedModuleAndAngleRange(0.017f, 0.034f, -90, -90);
//		ps.setAcceleration(0.00015f, -90);
		ps.setFadeIn(400, new AccelerateInterpolator());
		ps.setFadeOut(300, new AccelerateInterpolator());
		ps.emitWithGravity(arg0, Gravity.TOP | Gravity.CENTER_HORIZONTAL /*| Gravity.BOTTOM | Gravity.RIGHT*/, 8);
	}

}
