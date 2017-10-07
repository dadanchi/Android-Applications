package com.dadanchi.e_meal.Home;

import android.app.Activity;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.UserListener;

import io.reactivex.Observable;

/**
 * Created by dadanchi on 04/10/2017.
 */

public class HomePresenter implements HomeContracts.Presenter {
    private AuthRepository mRepository;
    private HomeContracts.View mView;

    public  HomePresenter(Activity activity) {
        this.mRepository = new AuthRepository(activity);
    }

    public BaseContracts.User getUser() {
        return mRepository.getCurrentUser();
    }

    public void setListener(UserListener userListener) {
        mRepository.addListener(userListener);
    }

    @Override
    public void subscribe(HomeContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}