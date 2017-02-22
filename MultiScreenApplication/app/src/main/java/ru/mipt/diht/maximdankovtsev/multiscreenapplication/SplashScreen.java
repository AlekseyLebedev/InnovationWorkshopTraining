package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // After 5 seconds run MainActivity
        int SPLASH_DELAY = 3000;
        Timer mainActivityTimer = new Timer();
        mainActivityTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // If reconstructed due to configuration change, two instance of MainActivity will be started.
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DELAY);
    }
}
