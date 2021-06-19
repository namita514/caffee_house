package com.example.caffe_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void signin(View view) {
        Intent intent11=new Intent(MainActivity.this,login.class);
        Toast.makeText(this, "you are login now", Toast.LENGTH_SHORT).show();
        startActivity(intent11);

    }

    public void signup(View view) {
        Intent intent12=new Intent(MainActivity.this,sign_up.class);
        Toast.makeText(this, "successfully registered", Toast.LENGTH_SHORT).show();
        startActivity(intent12);
    }


}