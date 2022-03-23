package com.example.moviestreaming.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreaming.Activity.Show_All;
import com.example.moviestreaming.R;
import com.example.moviestreaming.Activity.Show_Movies;

import java.util.List;

public class Move_Adapter extends RecyclerView.Adapter<Move_Adapter.Holder>{
    Context context;
    List<Movies> images;
    private int type;
    private boolean im=true;

    public Move_Adapter(Context context, List<Movies> images, int type) {
        this.context = context;
        this.images = images;
        this.type = type;
    }

    public Move_Adapter(Context context, List<Movies> images, int type, boolean im) {
        this.context = context;
        this.images = images;
        this.type = type;
        this.im = im;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (type==1){
            v= LayoutInflater.from(context).inflate(R.layout.move,null,false);
            return new Holder(v);
        }
        else {
            v= LayoutInflater.from(context).inflate(R.layout.strem,null,false);
            return new Holder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Movies i=images.get(position);
       if (type==1){
           Glide.with(context).load(i.getPoster()).into(holder.i);
       }
       else {
           Glide.with(context).load(i.getImage_i()).into(holder.s);
       }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (type==1){
                   Intent s=new Intent(context, Show_Movies.class);
                   s.putExtra("title",i.getTitle());
                   s.putExtra("Poster",i.getPoster());
                   s.putExtra("Plot",i.getPlot());
                   s.putExtra("Genre",i.getGenre());
                   s.putExtra("Company",i.getCompany());
                   s.putExtra("Mylist",i.isMylist());
                   s.putExtra("ImdbRating",i.getImdbRating());
                   s.putExtra("Runtime",i.getRuntime());
                   s.putExtra("Year",i.getYear());
                   s.putExtra("Typeapp",i.getType_app());
                   s.putExtra("Video",i.getVideo());
                   s.putExtra("Type",i.getType());
                   context.startActivity(s);
               }
               else {
                   Intent so=new Intent(context, Show_All.class);
                   so.putExtra("title_a",i.getTitle());
                   context.startActivity(so);
               }

           }
       });
//        holder.i.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent so=new Intent(context, Show_All.class);
//                so.putExtra("title_a",i.getTitle());
//                context.startActivity(so);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView i,s;

        public Holder(@NonNull View itemView) {
            super(itemView);
            i=itemView.findViewById(R.id.image_m);
            s=itemView.findViewById(R.id.image_s);
        }
    }
}
