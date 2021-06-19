package com.example.caffe_house;

public class my_order_modal {
    int image;
    String name,price,order_no;

    public void my_order_modal(int image, String name, String price, String order_no) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.order_no = order_no;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public  String getOrder_no() {
        return order_no;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
}
