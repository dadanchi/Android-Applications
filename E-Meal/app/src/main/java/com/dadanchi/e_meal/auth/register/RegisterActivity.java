package com.dadanchi.e_meal.auth.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthContracts;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.repositories.AuthRepository;

public class RegisterActivity extends AppCompatActivity {

    private AuthContracts.Presenter mPresenter;
    private RegisterView mView;
    private AuthRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPresenter = new AuthPresenter();
        mRepository = new AuthRepository(this);
        mPresenter.setRepository(mRepository);
        mView = RegisterView.create();

        mView.setPresenter(mPresenter);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_auth, mView)
                .commit();
    }

}
