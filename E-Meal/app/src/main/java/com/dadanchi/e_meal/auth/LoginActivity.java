package com.dadanchi.e_meal.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dadanchi.e_meal.R;

public class LoginActivity extends AppCompatActivity {

    private AuthPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginView view = LoginView.create();
        mPresenter = new AuthPresenter(this);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_login, view)
                .commit();
    }
}
