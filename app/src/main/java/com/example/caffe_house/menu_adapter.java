package com.example.caffe_house;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class menu_adapter extends RecyclerView.Adapter<menu_adapter.viewholder> {
    ArrayList<menu_modal> list;
    Context context;

    public menu_adapter(ArrayList<menu_modal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_menu,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final menu_modal model=list.get(position);
        holder.f1.setImageResource(model.getImage());
        holder.mainname.setText(model.getName   ());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12=new Intent(context,orderActivity.class);
                intent12.putExtra("image",model.getImage());
                intent12.putExtra("price",model.getPrice());
                intent12.putExtra("desc",model.getDescription());
                intent12.putExtra("name",model.getName());
                intent12.putExtra("type",1);
                context.startActivity(intent12);


            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder{
        ImageView f1;
        TextView mainname,price,description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            f1= itemView.findViewById(R.id.pic);
            mainname=itemView.findViewById(R.id.t1);
            price=itemView.findViewById(R.id.price);
            description=itemView.findViewById(R.id.t3);
        }
    }
}
