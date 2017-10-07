package com.dadanchi.e_meal.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.UserListener;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {
    @Inject
    HomeContracts.Presenter mPresenter;
    private UserListener mUserListener;

    private HomeView mView;
    private AuthRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mView = HomeView.create();
        mRepository = new AuthRepository(this);
        mPresenter = new HomePresenter();
        mView.setPresenter(mPresenter);

        mPresenter.setRepository(mRepository);

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
