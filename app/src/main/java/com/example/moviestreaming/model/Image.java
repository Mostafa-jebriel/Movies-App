package com.example.moviestreaming.model;

public class Image {
    private String image_s;
    private int image_i;

    public Image(int image_i) {
        this.image_i = image_i;
    }

    public int getImage_i() {
        return image_i;
    }

    public void setImage_i(int image_i) {
        this.image_i = image_i;
    }

    public String getImage_s() {
        return image_s;
    }

    public void setImage_s(String image_s) {
        this.image_s = image_s;
    }

    public Image(String image_s) {
        this.image_s = image_s;
    }
}
