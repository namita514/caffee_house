package com.example.caffe_house;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class my_order_adapter extends RecyclerView.Adapter<my_order_adapter.viewholder> {
    ArrayList<my_order_modal> list;
    Context context;

    public my_order_adapter(ArrayList<my_order_modal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_my_order,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  my_order_adapter.viewholder holder, int position) {
        final my_order_modal model=list.get(position);
        holder.f1.setImageResource(model.getImage());
        holder.mainname.setText(model.getName());
        holder.price.setText(model.getPrice());
holder.order_no.setText(model.getOrder_no());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,orderActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrder_no()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete iteam ")
                        .setMessage("Are you sure to delete this item ")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                DBhelper dBhelper= new DBhelper(context);
                                if(dBhelper.deleteorder(model.getOrder_no())>0)
                                {
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(context, "NOT Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {


                            }
                        }).show();


                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView f1;
        TextView mainname,price,order_no;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            f1= itemView.findViewById(R.id.pic);
            mainname=itemView.findViewById(R.id.solditemname);
            price=itemView.findViewById(R.id.solditemprice);
            order_no=itemView.findViewById(R.id.orderno);

    }
}}
