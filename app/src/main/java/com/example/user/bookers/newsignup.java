package com.example.user.bookers;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class newsignup extends AppCompatActivity {
    private Button ok;
    EditText ed1, ed2, ed3,ed4,ed5;
    FirebaseAuth auth;
    int i;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsignup);
        ed1 = (EditText) findViewById(R.id.e_name);
        ed2 = (EditText) findViewById(R.id.e_email);
        ed3 = (EditText) findViewById(R.id.e_phone);
        ed4 = (EditText) findViewById(R.id.e_pass);
        ed5 = (EditText) findViewById(R.id.e_confirmpass);

        auth = FirebaseAuth.getInstance();
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =ed1.getEditableText().toString();
                String email = ed2.getEditableText().toString();
                String number = ed3.getEditableText().toString();
                String pass = ed4.getEditableText().toString();
                String confirmps = ed5.getEditableText().toString();
                User user = new User(name,email,number,pass);

                if((!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(number)  && !TextUtils.isEmpty(name) )&& pass.equals(confirmps) ){

                    reg(user.getusername(),user.getEmail(),user.getPassword(),user.getMobile_number());



                }
                else {
                    Toast.makeText(getApplicationContext(),"filled all value",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void reg(final String name, final String email, final String pass,final String nm) {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                FirebaseUser firebaseUser =auth.getCurrentUser();
                String userid = firebaseUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference("User").child(userid);

                if(!task.isSuccessful()){
                    Toast.makeText(newsignup.this,"failed",Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String ,String> hashMap = new HashMap<String, String>();
                    hashMap.put("id",userid);
                    hashMap.put("username",name);
                    hashMap.put("email",email);
                    hashMap.put("pass",pass);
                    hashMap.put("Mobile_number",nm);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful()){
                                Toast.makeText(newsignup.this,"set on database;",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                intent.putExtra("number",nm);
                                getApplicationContext().startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(newsignup.this,"can' update;",Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }
            }
        });
    }
}

