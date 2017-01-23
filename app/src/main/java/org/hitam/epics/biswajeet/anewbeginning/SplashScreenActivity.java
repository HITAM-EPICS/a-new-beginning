package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class)); //moves from splash screen to home activity
                SplashScreenActivity.this.finish();     //CLOSES THE activity after returning back(app closes)
            }
        }, 5000);   //displays the splash screen for 300ms (3Seconds)
    }
}

//sale jopdike.. kaha mar gya?abey idhar hi hu app run kar raha hu
//?chala? nahi re is baar aur complex aara.. kya aa rha? tere phone ki memory bhar ja ri