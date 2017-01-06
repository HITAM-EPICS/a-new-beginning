package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class VolunteerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
    }



    public void donate(View view) {
          startActivity(new Intent(this,DonateActivity.class));
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    public void events(View view) {
        startActivity(new Intent(this,EventsActivity.class));
    }
}
