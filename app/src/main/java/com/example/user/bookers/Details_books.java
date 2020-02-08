package com.example.user.bookers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Details_books extends AppCompatActivity {

    TextView t,a,c,l,p,pic,mn;
    ImageView i;
    FirebaseAuth u;
    DatabaseReference user_reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdetailsfors);





        Intent intent = getIntent();


        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        String loc = intent.getStringExtra("LOCATION");
        String condition = intent.getStringExtra("CONDITION");
        String price = intent.getStringExtra("PRICE");
        String img = intent.getStringExtra("PIC");
        String uid = intent.getStringExtra("uid");
        String rent_id = intent.getStringExtra("RENT_ID");



        user_reference = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("Mobile_number");
        //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();

        t=(TextView) findViewById(R.id.bookTitle);
        a=(TextView) findViewById(R.id.book_Author);
        c=(TextView)findViewById(R.id.book_condition);
        l=(TextView) findViewById(R.id.book_Location);
        i=(ImageView)findViewById(R.id.singleImageview);
        final TextView mn = (TextView) findViewById(R.id.book_seller_Number);


        t.setText(title);
        a.setText(author);
        c.setText(condition);
        l.setText(loc);

        Picasso.with(getApplicationContext()).load(img).into(i);

        user_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mobile = dataSnapshot.getValue().toString();
                mn.setText("+88"+mobile);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







    }
}
