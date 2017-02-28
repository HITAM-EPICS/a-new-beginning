package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

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
                if (isNetworkAvailable()) {
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class)); //moves from splash screen to home activity
                    SplashScreenActivity.this.finish();     //CLOSES THE activity after returning back(app closes)
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                    builder.setTitle("Network Error")
                            .setMessage("Unable to connect to internet. Please check your internet connectivity and try again.")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SplashScreenActivity.this.finish();
                                }
                            });
                    builder.show();
                }
            }
        }, 5000);   //displays the splash screen for 300ms (3Seconds)
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

