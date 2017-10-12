package com.dadanchi.e_meal.auth;

import android.net.Uri;

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

    public void register(String email, String password, String firstName, String lastName, Uri uri) {
        mRepository.SignUpWithEmail(email, password, firstName, lastName, uri)
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
    public String getCurrentUsername() {
        return mRepository.getUsername();
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
