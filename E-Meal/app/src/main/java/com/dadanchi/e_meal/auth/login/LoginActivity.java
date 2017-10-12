package com.dadanchi.e_meal.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthContracts;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.repositories.AuthRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    AuthContracts.Presenter mPresenter;
    @Inject
    AuthRepository mRepository;

    private LoginView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        mView = LoginView.create();

        mPresenter.setRepository(mRepository);
        mView.setPresenter(mPresenter);
        
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_login, mView)
                .commit();
    }
}
