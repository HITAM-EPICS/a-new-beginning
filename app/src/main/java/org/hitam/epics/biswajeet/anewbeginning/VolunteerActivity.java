package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import org.hitam.epics.biswajeet.anewbeginning.support.Mailing;

public class VolunteerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
    }


    public void donate(View view) {
        startActivity(new Intent(this, DonateActivity.class));
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }
        CheckoutActivity.CheckOutList.clear();
        Mailing.userMail = null;
        finish();
    }

    public void events(View view) {
        startActivity(new Intent(this, EventsActivity.class));
    }

    public void additems(View view) {
        startActivity(new Intent(this, AddItemActivity.class));
    }
}
