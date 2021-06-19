package com.example.caffe_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class myorders extends AppCompatActivity {
RecyclerView r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        DBhelper dBhelper=new DBhelper(this);


        ArrayList<my_order_modal> list=dBhelper.getOrders();


       my_order_adapter adapter2=new my_order_adapter(list,
                this);
        r2.setAdapter(adapter2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        r2.setLayoutManager(layoutManager);

    }
    }
