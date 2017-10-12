package com.dadanchi.e_meal.profile;

import android.net.Uri;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dadanchi on 11/10/2017.
 */

public class ProfilePresenter implements ProfileContracts.Presenter {
    private ProfileContracts.View mView;
    private AuthRepository mRepository;

    @Override
    public void setRepository(AuthRepository repository) {
        mRepository = repository;
    }

    @Override
    public void updateUser(Uri uri) {
        mRepository.updateUserImage(uri);
    }

    @Override
    public void load() {
        mRepository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseContracts.User>() {
                    @Override
                    public void accept(BaseContracts.User user) throws Exception {
                        mView.setUser(user);
                    }
                });
    }

    @Override
    public void subscribe(ProfileContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
