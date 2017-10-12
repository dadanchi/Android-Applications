package com.dadanchi.e_meal.auth.register;

import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class RegisterActivity extends DaggerAppCompatActivity {

    @Inject
    AuthContracts.Presenter mPresenter;
    @Inject
    AuthRepository mRepository;

    private RegisterView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPresenter.setRepository(mRepository);
        mView = RegisterView.create();

        mView.setPresenter(mPresenter);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_auth, mView)
                .commit();
    }

}
