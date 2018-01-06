package com.example.loylogic.hackathon.Model;

/**
 * Created by loylogic on 06/01/18.
 */

public class Reaction {

    private String name;
    private String color;
    private String image;
    private String selectedImage;
    private boolean isSelected;
    public Reaction() {

    }

    public Reaction(String name, String color, String image, String selectedImage, boolean isSelected) {
        this.name = name;
        this.color = color;
        this.image = image;
        this.selectedImage = selectedImage;
        this.isSelected =isSelected;
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

    public String getSelectedImage() {
        return selectedImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

