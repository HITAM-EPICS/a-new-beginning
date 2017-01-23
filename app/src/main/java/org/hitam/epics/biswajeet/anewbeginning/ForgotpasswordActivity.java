package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends Activity {
    private EditText EmailAdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
       EmailAdEditText =(EditText)findViewById(R.id.EmailAdd);
    }

    public void resetpass(View view) {
       String EnterEmail=EmailAdEditText.getText().toString().trim();
        if(!EnterEmail.equals("")){
            FirebaseAuth.getInstance().sendPasswordResetEmail(EnterEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ForgotpasswordActivity.this, "Reset Link Sent to Registered Mail", Toast.LENGTH_SHORT).show();
                                   ForgotpasswordActivity.this.finish();
                }
            });
        }
    }
}
