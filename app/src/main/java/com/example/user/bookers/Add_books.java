package com.example.user.bookers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

/**
 * Created by User on 18/06/2019.
 */

public class Add_books extends MainActivity {
    private ImageButton imageBtn;
    private static final int GALLERY_REQUEST_CODE = 2;
    private Uri uri = null;
    private EditText title,author;
    private EditText price;
    private Button postBtn;
    private Spinner condition,location;
    private StorageReference storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef,databaseRefMC;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksddd);


         postBtn = (Button)findViewById(R.id.postBtn);
         title= (EditText)findViewById(R.id.bookTitle);
         price = (EditText)findViewById(R.id.bookPrice);
         author= (EditText)findViewById(R.id.bookAuthor);
         location = (Spinner)findViewById(R.id.spinner_chooseLocation);
         condition =(Spinner)findViewById(R.id.spinner_condition);


        storage = FirebaseStorage.getInstance().getReference();
        databaseRef = database.getInstance().getReference().child("Books");
        databaseRefMC = database.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid()).push();

        imageBtn = (ImageButton)findViewById(R.id.imageBtn);


        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "POSTING...", Toast.LENGTH_LONG).show();

                final String Title = title.getText().toString().trim();
                final String Author = author.getText().toString().trim();
                final String Price = price.getText().toString().trim();
                final String con = condition.getSelectedItem().toString();
                final String Location = location.getSelectedItem().toString();


                if (!TextUtils.isEmpty(Title) && !TextUtils.isEmpty(Price) && !TextUtils.isEmpty(Author)){

                    StorageReference filepath = storage.child("post_images").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Random r = new Random();
                            int i1 = r.nextInt()+1;
                            final String i2 = String.valueOf(i1);

                            @SuppressWarnings("VisibleForTests")
                            //getting the post image download url
                            final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                            final DatabaseReference newPost = databaseRef.push();
                            final DatabaseReference mc = databaseRefMC.child("MC_Books").child(mCurrentUser.getUid()).push();
                            final DatabaseReference forrent = FirebaseDatabase.getInstance().getReference().child(Title+i2).push();
                            final String t = Title.replace(" ","_");
                            final DatabaseReference titile_post = databaseRefMC.child(t).push();

                            //adding post contents to database reference
                            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    mc.child("title").setValue(Title);
                                    mc.child("price").setValue(Price);
                                    mc.child("imageUrl").setValue(downloadUrl.toString());
                                    mc.child("author").setValue(Author);
                                    mc.child("location").setValue(Location);
                                    mc.child("con").setValue(con);
                                    mc.child("RentId").setValue(Title+i2);
                                    mc.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    forrent.child("title").setValue(Title);
                                    forrent.child("price").setValue(Price);


                                    titile_post.child("title").setValue(Title);
                                    titile_post.child("price").setValue(Price);
                                    titile_post.child("imageUrl").setValue(downloadUrl.toString());
                                    titile_post.child("author").setValue(Author);
                                    titile_post.child("location").setValue(Location);
                                    titile_post.child("con").setValue(con);
                                    titile_post.child("RentId").setValue(FirebaseAuth.getInstance().getCurrentUser().getProviderId());
                                    titile_post.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){
                                                        Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });

                }
            }
        });

    }

    @Override
    // image from gallery result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            uri = data.getData();
            imageBtn.setImageURI(uri);
        }
    }
}
