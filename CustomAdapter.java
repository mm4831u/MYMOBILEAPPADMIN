package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    Activity activity;
    private ArrayList class_id, class_title, class_instructor, class_time, class_dow, class_capacity, class_dur, class_price;

    CustomAdapter (Activity activity,Context context,
                   ArrayList class_id,
                   ArrayList class_title,
                   ArrayList class_instructor,
                   ArrayList class_time,
                   ArrayList class_dow,
                   ArrayList class_capacity,
                   ArrayList class_dur,
                   ArrayList class_price){
        this.activity = activity;
        this.context = context;
        this.class_id = class_id;
        this.class_title = class_title;
        this.class_instructor = class_instructor;
        this.class_time = class_time;
        this.class_dow = class_dow;
        this.class_capacity = class_capacity;
        this.class_dur = class_dur;
        this.class_price = class_price;

    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.class_id_txt.setText(String.valueOf(class_id.get(position)));
        holder.class_title_txt.setText(String.valueOf(class_title.get(position)));
        holder.class_instructor_txt.setText(String.valueOf(class_instructor.get(position)));
        holder.class_time_txt.setText(String.valueOf(class_time.get(position)));
        holder.class_dow_txt.setText(String.valueOf(class_dow.get(position)));
        holder.class_capacity_txt.setText(String.valueOf(class_capacity.get(position)));
        holder.class_dur_txt.setText(String.valueOf(class_dur.get(position)));
        holder.class_price_txt.setText(String.valueOf(class_price.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(class_id.get(position)));
                intent.putExtra("title", String.valueOf(class_title.get(position)));
                intent.putExtra("instructor", String.valueOf(class_instructor.get(position)));
                intent.putExtra("time", String.valueOf(class_time.get(position)));
                intent.putExtra("dow", String.valueOf(class_dow.get(position)));
                intent.putExtra("capacity", String.valueOf(class_capacity.get(position)));
                intent.putExtra("dur", String.valueOf(class_dur.get(position)));
                intent.putExtra("price", String.valueOf(class_price.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return class_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView class_id_txt, class_title_txt, class_instructor_txt, class_time_txt, class_dow_txt, class_capacity_txt, class_dur_txt, class_price_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            class_id_txt = itemView.findViewById(R.id.class_id_txt);
            class_title_txt = itemView.findViewById(R.id.class_title_txt);
            class_instructor_txt = itemView.findViewById(R.id.class_instructor_txt);
            class_time_txt = itemView.findViewById(R.id.class_time_txt);
            class_dow_txt = itemView.findViewById(R.id.class_dow_txt);
            class_capacity_txt = itemView.findViewById(R.id.class_capacity_txt);
            class_dur_txt = itemView.findViewById(R.id.class_dur_txt);
            class_price_txt = itemView.findViewById(R.id.class_price_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
