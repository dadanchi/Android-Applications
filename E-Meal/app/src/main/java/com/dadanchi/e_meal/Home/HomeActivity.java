package com.dadanchi.e_meal.Home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.UserListener;

public class HomeActivity extends AppCompatActivity {
    private HomeContracts.Presenter mPresenter;
    private UserListener mUserListener;

    private HomeView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mView = HomeView.create();
        mPresenter = new HomePresenter(this);
        mView.setPresenter(mPresenter);

        mUserListener = new UserListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, mView)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setListener(mUserListener);
    }
}
