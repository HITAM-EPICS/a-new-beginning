package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                    if (user.isEmailVerified()) {
                        startActivity(new Intent(HomeActivity.this, VolunteerActivity.class));
//                    } else {
                        /*todo: display a message to verify email id */

//                        firebaseAuth.signOut();
//                    }
                }
            }
        };
    }


    public void Guest_menu(View view) {
        startActivity(new Intent(this, DonateActivity.class));
    }

    public void RegisterActivity(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void login(View view) {
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        if (email.getText().toString().trim().length()==0){
            email.setError("emailId Required");
            return;
        }

        if(password.getText().toString().length()==0){
            password.setError("password Required");
            return;
        }

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            email.setError("Invalid email or password");
                            password.setError("Invalid email or password");

                        }

                    }
                });
    }

    public void Forgotpass(View view) {
        startActivity(new Intent(this,ForgotpasswordActivity.class));
    }
}
