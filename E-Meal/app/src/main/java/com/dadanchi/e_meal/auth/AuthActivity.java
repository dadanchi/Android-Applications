package com.dadanchi.e_meal.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getIntent();

        AuthView view = AuthView.create();

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_auth, view)
                .commit();
    }

}
