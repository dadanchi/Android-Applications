package com.dadanchi.e_meal.auth;

import android.app.Activity;

import com.dadanchi.e_meal.repositories.AuthRepository;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class AuthPresenter {
    // implement interfaces
    AuthRepository mRepository;
    private Activity context;

    public  AuthPresenter(Activity activity) {
        this.context = activity;
        this.mRepository = new AuthRepository(context);
    }

    public void register(String email, String password, String firstName, String lastName) {
        mRepository.SignUpWithEmail(email, password, firstName, lastName);
    }

    public void logout() {
        mRepository.logout();
    }

    public void login(String email, String password) {
        mRepository.login(email, password);
    }
}
