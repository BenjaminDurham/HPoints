package com.aboundmediasolutions.services.hpoints.utilities;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.aboundmediasolutions.services.hpoints.MainActivity;
import com.aboundmediasolutions.services.hpoints.R;


public class StartSplashScreenLoader extends Activity {

    private static String TAG = "SplashScreenLoader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_splash_screen); // not needed - at the moment it is completely empty
        Log.v(TAG, "SplashScreen started");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartSplashScreenLoader.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 500);
        Log.v(TAG, "SplashScreen ended");
    }
}