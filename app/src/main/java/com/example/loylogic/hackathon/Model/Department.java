package com.example.loylogic.hackathon.Model;

/**
 * Created by loylogic on 06/01/18.
 */

public class Department {

    private String name;
    private String color;
    private String image;
    public Department() {

    }

    public Department(String name, String color, String image) {
        this.name = name;
        this.color = color;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }
}
