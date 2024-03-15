package com.entity;

public class Order {

    private int id;
    private int user_id;
    private String order_date;
    private double price;
    private String method_payment;
    private int state;

    public Order(int id, int user_id, String order_date) {
        this.id = id;
        this.user_id = user_id;
        this.order_date = order_date;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMethod_payment() {
        return method_payment;
    }

    public void setMethod_payment(String method_payment) {
        this.method_payment = method_payment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", order_date='" + order_date + '\'' +
                ", price=" + price +
                ", method_payment='" + method_payment + '\'' +
                '}';
    }
}
