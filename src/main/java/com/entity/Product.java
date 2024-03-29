package com.entity;

public class Product {

    private int id;
    private String name;
    private String cat;
    private double price;
    private int quantity;
    private int user_id;
    private String image;

    public Product(String name, String cat, double price, int quantity, int user_id) {
        this.name = name;
        this.cat = cat;
        this.price = price;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat='" + cat + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", user=" + user_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
