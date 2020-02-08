package com.example.user.bookers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.user.bookers.R.id.post_image;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<Book>listBook;
    private FirebaseAuth auth;

    public BookAdapter(List<Book> listBook) {
        this.listBook = listBook;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.carditemxml,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book ld=listBook.get(position);
        final User user = new User();
        holder.title.setText(ld.getTitle());
        holder.author.setText(ld.getauthor());
        holder.location.setText(ld.getLocation());
        holder.price.setText("Price "+ld.getPrice());
        Picasso.with(holder.cx).load(ld.getImageUrl()).into(holder.imageView);



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),ld.getTitle(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), Details_books.class);
                intent.putExtra("TITLE",ld.getTitle());
                intent.putExtra("AUTHOR",ld.getauthor());
                intent.putExtra("LOCATION",ld.getLocation());
                intent.putExtra("PRICE",ld.getTitle());
                intent.putExtra("uid",ld.getuid());
                intent.putExtra("PIC",ld.getImageUrl());
                v.getContext().startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {
        private TextView title,author,price,location;
        ImageView imageView;
        Context cx;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.book_title_txtview);
            author = (TextView) itemView.findViewById(R.id.book_auth_txtview);
            location = (TextView) itemView.findViewById(R.id.location);
            price = (TextView) itemView.findViewById(R.id.post_price);
            imageView = (ImageView) itemView.findViewById(post_image);
            cx = (Context) itemView.getContext();
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent);

            }

        }


}






