package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hitam.epics.biswajeet.anewbeginning.support.Mailing;

public class HomeActivity extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
//                    if (user.isEmailVerified()) {
                    Mailing.userMail = user.getEmail();
                    CheckoutActivity.CheckOutList.clear();
                    startActivity(new Intent(HomeActivity.this, VolunteerActivity.class));
//                    } else {
                        /*todo: display a message to verify email id */

//                        firebaseAuth.signOut();
//                    }
                }
                hideLoading();
            }
        };

        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                            String[] s = task.getException().getMessage().split(":");
                            if (s.length == 1) {
                                builder.setMessage(s[0]);
                            } else {
                                builder.setMessage(s[1]);
                            }

                            builder.setPositiveButton("Close", null);
                            builder.create().show();
                        }
                        hideLoading();

                    }
                });
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
        showLoading();
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        if (email.getText().toString().trim().length() == 0) {
            email.setError("emailId Required");
            hideLoading();
            return;
        }

        if (password.getText().toString().length() == 0) {
            password.setError("password Required");
            hideLoading();
            return;
        }

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            email.setError("Invalid email or password");
                            password.setError("Invalid email or password");

                            hideLoading();
                        }

                    }
                });
    }

    private void showLoading() {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        findViewById(R.id.content).setVisibility(View.GONE);
    }

    private void hideLoading() {
        findViewById(R.id.loading).setVisibility(View.GONE);
        findViewById(R.id.content).setVisibility(View.VISIBLE);
    }


    public void Forgotpass(View view) {
        startActivity(new Intent(this, ForgotpasswordActivity.class));
    }


    public void Help(View view) {
        startActivity(new Intent(this, HelpActivity.class));
    }
}
