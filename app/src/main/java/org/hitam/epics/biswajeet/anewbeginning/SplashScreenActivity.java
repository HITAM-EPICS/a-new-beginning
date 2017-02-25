package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static org.hitam.epics.biswajeet.anewbeginning.support.Mailing.organisationMail;
import static org.hitam.epics.biswajeet.anewbeginning.support.Mailing.vendorMail;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("payment");
        reference.child("organisation_mail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                organisationMail = dataSnapshot.getValue(String.class);
                Log.e("onDataChange: ", organisationMail);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                organisationMail = null;
            }
        });

        reference.child("vendor_mail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendorMail = dataSnapshot.getValue(String.class);
                Log.e("onDataChange: ", vendorMail);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                vendorMail = null;
            }
        });

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

