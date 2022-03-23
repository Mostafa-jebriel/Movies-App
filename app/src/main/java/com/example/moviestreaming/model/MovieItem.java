package com.example.moviestreaming.model;

import java.util.List;

public class MovieItem {
   String name;
   List<Movies> images;

    public MovieItem(String name, List<Movies> images) {
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movies> getImages() {
        return images;
    }

    public void setImages(List<Movies> images) {
        this.images = images;
    }
}
