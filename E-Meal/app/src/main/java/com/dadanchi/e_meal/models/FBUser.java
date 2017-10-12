package com.dadanchi.e_meal.models;

import android.net.Uri;

import com.dadanchi.e_meal.base.BaseContracts;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class FBUser implements BaseContracts.User {
    private final String id;
    private final String email;
    private final String name;
    private final String imageUrl;

    public FBUser(String id, String email, String name, String imageUrl) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getProfileImage() {
        return this.imageUrl;
    }
}
