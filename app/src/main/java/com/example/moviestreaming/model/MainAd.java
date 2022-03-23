package com.example.moviestreaming.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreaming.R;
import com.example.moviestreaming.Activity.Show_All;

import java.util.List;

public class MainAd extends RecyclerView.Adapter<MainAd.Holder>{
    Context context;
    List<MovieItem> movieItems;
    Move_Adapter moveAdapter;


    public MainAd(Context context, List<MovieItem> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.main_moves,null,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MovieItem m=movieItems.get(position);
        holder.t.setText(m.getName());
        moveAdapter=new Move_Adapter(context,m.getImages(),1);
        holder.s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent so=new Intent(context, Show_All.class);
                so.putExtra("title_a",m.getName());
                context.startActivity(so);

            }
        });
        holder.r.setHasFixedSize(true);
        holder.r.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.r.setAdapter(moveAdapter);

    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView t,s;
        RecyclerView r;

        public Holder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.name_c);
            s=itemView.findViewById(R.id.show_all);
            r=itemView.findViewById(R.id.list_move);
        }
    }
}
