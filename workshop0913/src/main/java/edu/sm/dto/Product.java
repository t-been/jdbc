package edu.sm.dto;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private int price;
    private String size;
    private String color;
    private Date registrationDate;

    // 생성자
    public Product(int id, String name, int price, String size, String color, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.registrationDate = registrationDate;
    }

    // Getter와 Setter
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}