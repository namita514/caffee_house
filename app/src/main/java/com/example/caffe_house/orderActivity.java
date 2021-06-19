package com.example.caffe_house;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.caffe_house.databinding.ActivityOrderBinding;

public class orderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBhelper dBhelper=new DBhelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {
        final int image = getIntent().getIntExtra("image", 0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String name = getIntent().getStringExtra("name");
        final String description = getIntent().getStringExtra("desc");

            binding.orderimage.setImageResource(image);
            binding.detailprice.setText(String.format("%d", price));
            binding.des.setText(description);
            binding.foody.setText(name);

            binding.insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isinserted = dBhelper.insertOrder(
                            binding.nameuse.getText().toString(),
                            binding.phone.getText().toString(),
                            price,
                            description,
                            name,
                            image,
                            Integer.parseInt(binding.textView4.getText().toString())
                    );
                    if (isinserted) {
                        Toast.makeText(orderActivity.this, "Thank you ,you will recieve your order soon", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(orderActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }
        else{
            int id =getIntent().getIntExtra("id",0);
            Cursor cursor= dBhelper.getorderbyid(id);
            int image1=cursor.getInt(4);
            binding.orderimage.setImageResource(image1); // image index is 3 db helper
            binding.detailprice.setText(String.format("%d", cursor.getInt(3)));
            binding.foody.setText(cursor.getString(6)); //1
            binding.des.setText(cursor.getString(5));
            binding.nameuse.setText(cursor.getString(1));
            binding.phone.setText(cursor.getString(2));
            binding.insert.setText("update now");
            binding.insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isupdated= dBhelper.updateOrder(
                            binding.nameuse.getText().toString(),
                            binding.phone.getText().toString(),
                            Integer.parseInt(binding.detailprice.getText().toString()),
                            image1,
                            binding.des.getText().toString(),
                            binding.foody.getText().toString(),
                            1,
                            id
                    );
                    if(isupdated)
                    {
                        Toast.makeText(orderActivity.this, "updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(orderActivity.this, "Faild", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }
        }
    }
