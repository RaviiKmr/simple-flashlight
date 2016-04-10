package com.raviikmr.flashlight;

import java.util.Random;

import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.raviikmr.flashlightx.R;

public class MainActivity extends Activity {

	// detect flash is on or off
	private boolean isLightOn = false;
	private Camera camera;
	private ImageButton button;
	public ImageButton colorch;
	public RelativeLayout rel_layout;
	InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// ads
		// ///////////
		// Insert the Ad Unit ID
		// Prepare the Interstitial Ad
		interstitial = new InterstitialAd(MainActivity.this);
		// Insert the Ad Unit ID
		interstitial.setAdUnitId("ca-app-pub-XXXXXXXXXXXXX");
		// Locate the Banner Ad in activity_main.xml
		AdView adView = (AdView) this.findViewById(R.id.adView);

		// Request for Ads
		AdRequest adRequest = new AdRequest.Builder()

		// Add a test device to show Test Ads
		// .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		// .addTestDevice("F016B34915C819DB0E98C85CAE01F6B2");
		// .addTestDevice("0123456789ABCDEF")
				.build();

		// Load ads into Banner Ads
		adView.loadAd(adRequest);

		// Load ads into Interstitial Ads
		interstitial.loadAd(adRequest);

		// /////////////////////
		// ads end
		rel_layout = (RelativeLayout) findViewById(R.id.relativeLayout1);
		rel_layout.setBackgroundColor(Color.parseColor("#e74c3c"));
		button = (ImageButton) findViewById(R.id.buttonFlashlight);
		colorch = (ImageButton) findViewById(R.id.imageButton1);
		Context context = this;
		PackageManager pm = context.getPackageManager();
		// if device support camera
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}

		camera = Camera.open();
		final Parameters p = camera.getParameters();

		// turn flashlight ON when first the app is started
		Log.i("info", "torch is turn on!");
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(p);
		camera.startPreview();
		isLightOn = true;
		button.setImageResource((R.drawable.s_on));
		// //

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				arg0.playSoundEffect(SoundEffectConstants.CLICK);

				if (isLightOn) {
					Log.i("info", "torch is turn off!");
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLightOn = false;
					button.setImageResource((R.drawable.s_off));
				} else {
					Log.i("info", "torch is turn on!");
					p.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(p);
					camera.startPreview();
					isLightOn = true;
					button.setImageResource((R.drawable.s_on));
				}
			}
		});

		colorch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				arg0.playSoundEffect(SoundEffectConstants.CLICK);
				if (interstitial.isLoaded()) {
					interstitial.show();
				} else {
					AdRequest adRequest = new AdRequest.Builder()

					// Add a test device to show Test Ads
					// .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					// .addTestDevice("F016B34915C819DB0E98C85CAE01F6B2")
							.build();

					// Load ads into Interstitial Ads
					interstitial.loadAd(adRequest);

					// Prepare an Interstitial Ad Listener

				}

				int min = 1;
				int max = 6;
				Random r = new Random();
				int i1 = r.nextInt(max - min + 1);
				switch (i1) {
				case 1:
					rel_layout.setBackgroundColor(Color.parseColor("#1abc9c"));
					break;
				case 2:
					rel_layout.setBackgroundColor(Color.parseColor("#3498db"));
					break;
				case 3:
					rel_layout.setBackgroundColor(Color.parseColor("#8e44ad"));
					break;
				case 4:
					rel_layout.setBackgroundColor(Color.parseColor("#34495e"));
					break;
				case 5:
					rel_layout.setBackgroundColor(Color.parseColor("#d35400"));
					break;
				default:
					rel_layout.setBackgroundColor(Color.parseColor("#7f8c8d"));
				}

			}
		});

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (camera != null) {
			camera.release();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.website:
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.raviikmr.github.io"));
			startActivity(intent);
			return true;
		case R.id.change_c:

			if (interstitial.isLoaded()) {
				interstitial.show();
			} else {
				AdRequest adRequest = new AdRequest.Builder()

				// Add a test device to show Test Ads
				// .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				// .addTestDevice("F016B34915C819DB0E98C85CAE01F6B2")
						.build();

				// Load ads into Interstitial Ads
				interstitial.loadAd(adRequest);

				// Prepare an Interstitial Ad Listener

			}

			int min = 1;
			int max = 6;
			Random r = new Random();
			int i1 = r.nextInt(max - min + 1);
			switch (i1) {
			case 1:
				rel_layout.setBackgroundColor(Color.parseColor("#1abc9c"));
				break;
			case 2:
				rel_layout.setBackgroundColor(Color.parseColor("#3498db"));
				break;
			case 3:
				rel_layout.setBackgroundColor(Color.parseColor("#8e44ad"));
				break;
			case 4:
				rel_layout.setBackgroundColor(Color.parseColor("#34495e"));
				break;
			case 5:
				rel_layout.setBackgroundColor(Color.parseColor("#d35400"));
				break;
			default:
				rel_layout.setBackgroundColor(Color.parseColor("#7f8c8d"));
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
