package com.dadanchi.e_meal.models;

import com.dadanchi.e_meal.base.BaseContracts;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class FBUser implements BaseContracts.User {
    private FirebaseUser user;

    public FBUser(FirebaseUser user) {
        this.user = user;
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public String getId() {
        return this.user.getUid();
    }
}
