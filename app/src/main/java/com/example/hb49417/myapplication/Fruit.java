package com.example.hb49417.myapplication;

/**
 * Created by HB49417 on 14/7/2017.
 */

public class Fruit {

    private String name;

    Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
