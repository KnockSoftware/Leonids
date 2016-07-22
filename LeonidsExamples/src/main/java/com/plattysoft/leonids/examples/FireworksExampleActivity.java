package com.plattysoft.leonids.examples;

import com.plattysoft.leonids.examples.R;
import com.plattysoft.leonids.ParticleSystem;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class StringUtils {

	public static Bitmap drawBitmap(String text){
		Paint paint=new Paint();

		Bitmap b = Bitmap.createBitmap(20, 20, Bitmap.Config.ALPHA_8);
		Canvas c = new Canvas(b);
		c.drawText(text, 0, 20 / 2, paint);
		return b;
	}

	public static byte[] getPixels(Bitmap b) {
		ByteBuffer buffer = ByteBuffer.allocate(b.getByteCount());
		b.copyPixelsToBuffer(buffer);
		return buffer.array();
	}
	public static boolean isCharacterMissingInFont(String ch) {
		String missingCharacter = "\u2936"; // reserved code point in the devanagari block (should not exist).
		byte[] b1 = getPixels(drawBitmap(ch));
		byte[] b2 = getPixels(drawBitmap(missingCharacter));
		return Arrays.equals(b1, b2);
	}
}

public class FireworksExampleActivity extends AppCompatActivity implements OnClickListener {
	private ParticleSystem mParticleSystem;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);

		Slide slideTransition = new Slide();
		slideTransition.setSlideEdge(Gravity.LEFT);
		slideTransition.setDuration(500);
		getWindow().setReenterTransition(slideTransition);
		getWindow().setExitTransition(slideTransition);

		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

		final View view = (View) findViewById(R.id.emitter_space);

		if (view.getHeight() == 0) {
			view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
				@Override
				public void onGlobalLayout() {
					startEmoji();
					view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				}
			});
		} else {
			startEmoji();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mParticleSystem != null) {
			mParticleSystem.stopEmitting();
//            mParticleSystem = null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mParticleSystem != null) {
			mParticleSystem.cancel();
			mParticleSystem = null;
		}
	}

	public void startEmoji() {
		String emojisString = "\uD83D\uDE4C \uD83C\uDF02 \uD83C\uDF24 \uD83C\uDF27 ⛄️ \uD83D\uDCA7 \uD83D\uDEB4 \uD83D\uDEB2 \uD83C\uDF08 \uD83C\uDF20 ❤️ \uD83D\uDC99 \uD83D\uDC9C \uD83D\uDC9A \uD83D\uDC9B \uD83C\uDF96 \uD83C\uDFC5 \uD83C\uDFC6 \uD83C\uDF97 \uD83D\uDCAB \uD83C\uDF41 \uD83C\uDFA9 \uD83D\uDC52";
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();

		String[] emojis = emojisString.split(" ");
		for (String emoji: emojis) {
			if(!StringUtils.isCharacterMissingInFont(emoji)) {
				bitmaps.add(emojiBitmap(emoji));
			}
		}

		Collections.shuffle(bitmaps);

		if (mParticleSystem == null) {
			mParticleSystem = new ParticleSystem(this, 100, bitmaps, 30000, R.id.background_hook);
			mParticleSystem.setAcceleration(0.00000f, 90);
			mParticleSystem.setSpeedByComponentsRange(0f, 0f, 0.04f, 0.07f);
			mParticleSystem.setFadeOut(400, new AccelerateInterpolator());
		}
		mParticleSystem.emitWithGravity(findViewById(R.id.emitter_space), Gravity.BOTTOM, 2);
	}

	Bitmap emojiBitmap(String emoji) {
		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = Bitmap.createBitmap(200, 200, conf);
		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE); // Text Color
		paint.setTextSize((int) (140));
		paint.setStrokeWidth(12); // Text Size

		Rect bounds = new Rect();
		paint.getTextBounds(emoji, 0, emoji.length(), bounds);

		int x = (bitmap.getWidth() - bounds.width())/2;
		int y = (bitmap.getHeight() + bounds.height())/2;

		canvas.drawText(emoji, x, y, paint);

		return bitmap;
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(FireworksExampleActivity.this, DustExampleActivity.class);
		FireworksExampleActivity.this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(FireworksExampleActivity.this).toBundle());
	}
}
