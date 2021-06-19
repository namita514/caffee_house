package com.example.caffe_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caffe_house.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class menu extends AppCompatActivity {
 RecyclerView r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        ArrayList<menu_modal> list=new ArrayList<>();
        r1=findViewById(R.id.r1);
        list.add(new menu_modal(R.drawable.food1,"Burger","5","this is deliceous"));
        list.add(new menu_modal(R.drawable.food2,"pizzq","10","this is deliceous"));
        list.add(new menu_modal(R.drawable.food1,"Burger","5","this is deliceous"));
        list.add(new menu_modal(R.drawable.food2,"pizzq","10","this is deliceous"));
        list.add(new menu_modal(R.drawable.food1,"Burger","5","this is deliceous"));
        list.add(new menu_modal(R.drawable.food2,"pizzq","10","this is deliceous"));
        list.add(new menu_modal(R.drawable.food1,"Burger","5","this is deliceous"));
        list.add(new menu_modal(R.drawable.food2,"pizzq","10","this is deliceous"));
       menu_adapter adapter= new menu_adapter(list,this);
        r1.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        r1.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.i1:
                startActivity(new Intent(menu.this,myorders.class));
                break;
            case R.id.i2:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(menu.this,login.class));


                break;

        }
        return super.onOptionsItemSelected(item);
    }}