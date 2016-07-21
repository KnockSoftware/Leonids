package com.plattysoft.leonids.examples;

import com.plattysoft.leonids.examples.R;
import com.plattysoft.leonids.ParticleSystem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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

public class FireworksExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		String emojisString = "\uD83D\uDC4D \uD83D\uDC4E \uD83D\uDE4C \uD83C\uDF02 \uD83C\uDF44 \uD83C\uDF24 \uD83C\uDF27 ⛄️ \uD83D\uDCA7 \uD83D\uDEB4 \uD83D\uDEB2 \uD83D\uDE80 \uD83C\uDF08 \uD83C\uDF20 \uD83C\uDF89 ❤️ \uD83D\uDC99 \uD83D\uDC9C \uD83D\uDC9A \uD83D\uDC9B \uD83D\uDCE2 \uD83C\uDF96 \uD83C\uDFC5 \uD83C\uDFC6 \uD83C\uDF97 \uD83D\uDCAB \uD83C\uDF41 \uD83C\uDFA9 \uD83D\uDC7B \uD83D\uDC52";
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();

		String[] emojis = emojisString.split(" ");
		for (String emoji: emojis) {
			if(!StringUtils.isCharacterMissingInFont(emoji)) {
				bitmaps.add(emojiBitmap(emoji));
			}
		}

		ParticleSystem ps = new ParticleSystem(this, 100, bitmaps, 30000, R.id.background_hook);
		ps.setAcceleration(0.00000f, 90);
				ps.setSpeedByComponentsRange(0f, 0f, 0.04f, 0.07f);
				ps.setFadeOut(400, new AccelerateInterpolator());
				ps.emitWithGravity(findViewById(R.id.emitter_space), Gravity.BOTTOM, 2);
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

}
