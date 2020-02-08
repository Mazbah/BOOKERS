package com.example.user.bookers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class My_Rents extends AppCompatActivity {
    Button su, see;
    EditText code;
    DatabaseReference code_reference, myrents, mt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__rents);
        su = (Button) findViewById(R.id.submitcode);
        code = (EditText) findViewById(R.id.code);

        see = (Button) findViewById(R.id.see_the_list);


        final DatabaseReference mt = FirebaseDatabase.getInstance().getReference().child("myrents");

        final Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String presentDate = df.format(date);

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RENTLIST.class));
            }
        });


        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String c = code.getEditableText().toString();
                code_reference = FirebaseDatabase.getInstance().getReference().child(c);
                final DatabaseReference myrents = FirebaseDatabase.getInstance().getReference().child("myrents").push();
                code_reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                                Book l = npsnapshot.getValue(Book.class);
                                myrents.child("title").setValue(l.getTitle());
                                myrents.child("price").setValue(l.getPrice());
                                myrents.child("submit_date").setValue(presentDate);

                                startActivity(new Intent(getApplicationContext(),RENTLIST.class));

                            }


                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }


        });
    }
}
