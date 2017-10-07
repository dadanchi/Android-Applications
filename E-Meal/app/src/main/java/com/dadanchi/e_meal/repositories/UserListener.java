package com.dadanchi.e_meal.repositories;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.dadanchi.e_meal.auth.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dadanchi on 06/10/2017.
 */

public class UserListener {
    private final Activity mContext;
    private FirebaseAuth.AuthStateListener mListener;

    public UserListener(Activity context) {
        mContext = context;
        mListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if( firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            }
        };
    }

    public FirebaseAuth.AuthStateListener getListener() {
        return mListener;
    }

}
