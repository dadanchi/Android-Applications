package com.dadanchi.e_meal.models;

import java.util.HashSet;

/**
 * Created by dadanchi on 10/10/2017.
 */

public class Recipe {
    private String title;
    private String description;
    private String imgUrl;

    public Recipe(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public Recipe() {

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
