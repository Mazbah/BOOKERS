package com.example.user.bookers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signup,login;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public EditText mail,pass;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginui);
        signup = (Button)findViewById(R.id.SignUpBtn);
        login = (Button)findViewById(R.id.loginBtn);
        final EditText mail = (EditText)findViewById(R.id.login_email);
        final EditText pass = (EditText)findViewById(R.id.login_password);
        auth = FirebaseAuth.getInstance();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,newsignup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ml =mail.getEditableText().toString();
                String ps =pass.getEditableText().toString();
                if(!ml.isEmpty() && !ps.isEmpty()) {
                    log(ml, ps);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter the email and password field",Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private void log(String ml, String ps) {
        auth.signInWithEmailAndPassword(ml, ps).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(MainActivity.this,Homepage.class));
                }

                // ...
            }
        });
    }


}
