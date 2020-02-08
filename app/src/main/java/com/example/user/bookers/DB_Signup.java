package com.example.user.bookers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class DB_Signup  {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference;
    int stat = 0;

    public  int registration(final User user ){

        final String email = user.getEmail();
        final String pass = user.getPassword();

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser =auth.getCurrentUser();
                String userid = firebaseUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference("User").child(userid);
                if(!task.isSuccessful()){
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail());

                }
                else {
                    HashMap<String ,String> hashMap = new HashMap<String, String>();
                    hashMap.put("id",userid);
                    hashMap.put("username",user.getusername());
                    hashMap.put("email",email);
                    hashMap.put("pass",pass);
                    hashMap.put("Mobile_number",user.getMobile_number());

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful()){
                                Log.d(TAG, "onAuthStateChanged:signed_in haha:" + user.getEmail());
                                stat = 5;

                            }
                            else {
                                Log.d(TAG, "onAuthStateChanged:signed_in: hah" + user.getEmail());

                            }
                        }
                    });



                }
            }
        });
        return stat;
    }
}
