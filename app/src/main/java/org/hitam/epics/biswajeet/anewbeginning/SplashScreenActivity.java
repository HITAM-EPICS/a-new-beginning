package org.hitam.epics.biswajeet.anewbeginning;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class)); //moves from splash screen to home activity
                SplashScreenActivity.this.finish();     //CLOSES THE activity after returning back(app closes)
            }
        }, 3000);   //displays the splash screen for 300ms (3Seconds)
    }
}

