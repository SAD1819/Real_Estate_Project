package com.example.real_estate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends  FirebaseRecyclerAdapter<Items,MyAdapter.MyViewHolder> {

    private Context context;


    public MyAdapter(@NonNull FirebaseRecyclerOptions<Items> options,Context context) {

        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Items model) {

        holder.txtbname.setText(model.getPropertyname());
        holder.txtaddress.setText(model.getAddress());
        holder.txttype.setText(model.getArea());
        holder.txtarea.setText(model.getPrice());
        holder.txtcontact.setText(model.getContactno());



        Glide.with(holder.img1.getContext()).load(model.getImageurl()).into(holder.img1);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)

                        .setContentHolder(new ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View view1 = (LinearLayout) dialogPlus.getHolderView();
                final EditText edname = view1.findViewById(R.id.proname);
                EditText edarea = view1.findViewById(R.id.edarea);
                EditText edprice = view1.findViewById(R.id.edprice);
                EditText edaddress = view1.findViewById(R.id.edaddress);
                EditText edcontact = view1.findViewById(R.id.edcontact);

                edname.setText(model.getPropertyname());
                edarea.setText(model.getArea());
                edaddress.setText(model.getAddress());
                edprice.setText(model.getPrice());
                edcontact.setText(model.getContactno());


                Button btn = view1.findViewById(R.id.btnupdate);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("propertyname",edname.getText().toString());
                        map.put("area",edarea.getText().toString());
                        map.put("address",edaddress.getText().toString());
                        map.put("price",edprice.getText().toString());
                        map.put("contactno",edcontact.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("data").child(getRef(position).getKey())
                                .updateChildren(map)

                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });


                    }
                });
                dialogPlus.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("data").child(getRef(position).getKey())
                        .setValue(null)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtarea,txtcontact;
        ImageView update,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtarea = itemView.findViewById(R.id.edarea);
            txtcontact = itemView.findViewById(R.id.edcontactno);


            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}