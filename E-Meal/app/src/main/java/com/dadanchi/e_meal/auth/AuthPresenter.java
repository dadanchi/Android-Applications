package com.dadanchi.e_meal.auth;

import com.dadanchi.e_meal.repositories.AuthRepository;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class AuthPresenter implements AuthContracts.Presenter {
    // implement interfaces
    private AuthRepository mRepository;
    private AuthContracts.View mView;

    public  AuthPresenter() {
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

    public void setRepository(AuthRepository repository) {
        mRepository = repository;
    }

    @Override
    public void subscribe(AuthContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
