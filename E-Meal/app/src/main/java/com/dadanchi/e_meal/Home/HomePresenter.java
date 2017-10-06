package com.dadanchi.e_meal.Home;

import android.app.Activity;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.UserListener;

import io.reactivex.Observable;

/**
 * Created by dadanchi on 04/10/2017.
 */

public class HomePresenter {
    private AuthRepository mRepository;
    private Activity context;

    public  HomePresenter(Activity activity) {
        this.context = activity;
        this.mRepository = new AuthRepository(context);
    }

    public BaseContracts.User getUser() {
        return mRepository.getCurrentUser();
    }

    public void setListener(UserListener userListener) {
        mRepository.addListener(userListener);
    }
}
