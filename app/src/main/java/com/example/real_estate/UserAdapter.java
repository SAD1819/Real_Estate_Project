package com.example.real_estate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    ArrayList<User> mList;
    Context context;

    public UserAdapter(ArrayList<User> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userlist,parent,false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = mList.get(position);
        holder.txtname.setText(user.getName());
        holder.txtaddress.setText(user.getNumber());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtname,txtaddress;

        public UserViewHolder(@NonNull View itemView) {

            super(itemView);
            txtname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);

        }
    }
}
