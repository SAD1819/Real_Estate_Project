package com.example.real_estate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Items> mList;

    private RecyclerViewClickListener listener;

    public Adapter(ArrayList<Items> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items vacancy1 = mList.get(position);
        holder.txtbname.setText(vacancy1.getPropertyname());
        holder.txtaddress.setText(vacancy1.getAddress());
        holder.txttype.setText(vacancy1.getArea());
        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);
//        holder.img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context,userdetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("url",vacancy1.getImageurl());
//                bundle.putString("proname",vacancy1.getPropertyname());
//                bundle.putString("address",vacancy1.getAddress());
//                bundle.putString("area",vacancy1.getArea());
//                bundle.putString("price", vacancy1.getPrice());
//                bundle.putString("contactno",vacancy1.getContactno());
//
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView img1;
        TextView txtbname,txtaddress,txttype;

    public ViewHolder(@NonNull View itemView) {

        super(itemView);

        img1 =itemView.findViewById(R.id.img1);
        txtbname = itemView.findViewById(R.id.nametext);
        txtaddress = itemView.findViewById(R.id.coursetext);
        txttype = itemView.findViewById(R.id.emailtext);
        itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
