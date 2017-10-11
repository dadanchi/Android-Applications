package com.dadanchi.e_meal.auth;

import android.content.Intent;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.repositories.AuthRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        mRepository.SignUpWithEmail(email, password, firstName, lastName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isRegistered) throws Exception {
                        mView.isUserIn(isRegistered);
                    }
                });


    }

    @Override
    public String getCurrentUserName() {
        return mRepository.getCurrentUser().getName();
    }

    public void logout() {
        mRepository.logout();
    }

    public void login(String email, String password) {
        mRepository.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isUserIn) throws Exception {
                        mView.isUserIn(isUserIn);
                    }
                });

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
