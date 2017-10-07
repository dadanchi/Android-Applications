package com.dadanchi.e_meal.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthContracts;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.repositories.AuthRepository;

public class LoginActivity extends AppCompatActivity {

    private AuthContracts.Presenter mPresenter;
    private LoginView mView;
    private AuthRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        mView = LoginView.create();
        mPresenter = new AuthPresenter();
        mRepository = new AuthRepository(this);
        mPresenter.setRepository(mRepository);
        mView.setPresenter(mPresenter);
        
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_login, mView)
                .commit();
    }
}
