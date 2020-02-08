package com.example.user.bookers;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.ViewHolder>{
    private List<Book>listBook;
    private FirebaseAuth auth;

    public BookAdapter2(List<Book> listBook) {
        this.listBook = listBook;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookslooklistui,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book ld=listBook.get(position);
        final User user = new User();
        holder.title.setText(ld.getTitle());
        holder.desc.setText("You subscription has been confirmed from the date of "+ld.getSubmit_date());
        holder.day.setText(String.valueOf(calcDiff(ld.getSubmit_date())));




        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),ld.getTitle(),Toast.LENGTH_LONG).show();


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    long calcDiff(String rentDate){

        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String presentDate = df.format(c);

        String[] RD = rentDate.split("-");
        String[] PD = presentDate.split("-");

        int Ryear = Integer.parseInt(RD[2]);
        int Pyear = Integer.parseInt(PD[2]);

        int RMon = Integer.parseInt(RD[1]);
        int PMon = Integer.parseInt(PD[1]);

        int RDay = Integer.parseInt(RD[0]);
        int PDay = Integer.parseInt(PD[0]);

        int day = 0;

        if(Pyear==Ryear){
            day+=(PMon-RMon-1)*30+PDay+(30-RDay);
        }
        else{
            day+=(12-RMon)*30+(PMon-2)*30+PDay+(30-RDay);
        }

        return day;
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {
        private TextView title,desc,day;

        Context cx;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.book_title);
            desc = (TextView) itemView.findViewById(R.id.book_desc);
            cx = (Context) itemView.getContext();
            day = (TextView)itemView.findViewById(R.id.day_count);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent2);

        }

    }


}






