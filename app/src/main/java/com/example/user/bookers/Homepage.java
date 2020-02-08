package com.example.user.bookers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    EditText editText;
    private CardView B1,B2,B3,B4,b6,b5;
    public Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hompageui);

        final Intent intent = getIntent();
        final String number = intent.getStringExtra("number");
        B1 = (CardView)findViewById(R.id.b1);
        B2 = (CardView)findViewById(R.id.b2);
        B3 = (CardView)findViewById(R.id.b3);
        B4 = (CardView)findViewById(R.id.b4);
        b6 = (CardView)findViewById(R.id.b6);
        b5 =(CardView)findViewById(R.id.b5);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,My_Collection.class));
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add_books.class);
                intent.putExtra("number",number);
                getApplicationContext().startActivity(intent);

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        search = (Button)findViewById(R.id.search);
        editText = (EditText)findViewById(R.id.search_edit);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_search_text = editText.getEditableText().toString();
                final String search_text= get_search_text.replaceAll(" ","_");
                if(!search_text.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), Search_Result.class);
                    intent.putExtra("title", search_text);
                    startActivity(intent);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),My_Rents.class));
            }
        });
    }
}
