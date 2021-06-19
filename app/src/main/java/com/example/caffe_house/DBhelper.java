package com.example.caffe_house;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    final static String DBNAME="database1.db";
    final static int DBVERSION=1;
    public DBhelper(@Nullable Context context) {

        super(context, DBNAME,null , DBVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(
        "create table orders"+
                "(id integer primary key autoincrement,"+
                "name text,"+
                "phone text,"+
                "price int,"+
                "image int,"+
                "description text,"+
                "quantity int,"+
                "foodname text)"


);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP table if exists orders");
onCreate(db);
    }
    public boolean insertOrder(String name, String phone , int price, String description, String foodname, int image, int quantity){
SQLiteDatabase database=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",description);
        values.put("foodname",foodname);
        values.put("quantity",quantity);
        long id=database.insert("orders",null,values);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList<my_order_modal> getOrders(){
        ArrayList<my_order_modal> orders= new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
       Cursor cursor= database.rawQuery("select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext()){
             my_order_modal model=new my_order_modal();
                model.setOrder_no(cursor.getString(0));
                model.setName(cursor.getString(1));


                model.setImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }
    public Cursor getorderbyid(int id)
    {

        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor= database.rawQuery("select * from ordertb where id = "+ id ,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        return cursor;
    }

    // updating order
    public boolean updateOrder(String name,String phone,int price,int image,String dsc,String foodName,int quant,int id)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values=new ContentValues();
        /*
        name=1
        phone =2
        price =3
        image =4
        quant=5
        dsc=6
        foodname=7
         */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("Quantity",quant);
        values.put("description",dsc);
        values.put("foodname",foodName);
        long row=database.update("orders",values,"id ="+id,null);
        if(row<=0)
        {
            return  false;
        }
        else
        {
            return true;
        }

    }

    public int deleteorder(String id )
    {
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id ="+id,null);
    }

}
