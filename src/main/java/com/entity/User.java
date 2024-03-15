package com.entity;

public class User {

    private int id;
    private String name;
    private String password;
    private String email;
    private String city;
    private int postal;
    private String adress;
    private String role;
    private String salt;
    private double fidelity_point;

    public double getFidelity_point() {
        return fidelity_point;
    }

    public void setFidelity_point(double fidelity_point) {
        this.fidelity_point = fidelity_point;
    }

    public User(){
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getPostal() {
        return postal;
    }

    public String getAdress() {
        return adress;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", postal=" + postal +
                ", adress='" + adress + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

