package com.dadanchi.e_meal.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;

public class RegisterActivity extends AppCompatActivity {

    private AuthPresenter presenter;
    private RegisterView registerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final RegisterActivity context = this;
        this.presenter = new AuthPresenter(this);
        registerView = RegisterView.create();

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_auth, registerView)
                .commit();
    }

}
