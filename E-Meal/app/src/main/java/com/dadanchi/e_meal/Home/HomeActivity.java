package com.dadanchi.e_meal.Home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.UserListener;

import javax.inject.Inject;


public class HomeActivity extends BaseDrawerActivity {
    @Inject
    HomeContracts.Presenter mPresenter;
    @Inject
    AuthRepository mRepository;

    // TODO -> Inject listener
    private UserListener mUserListener;
    private HomeView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);

        mView = HomeView.create();
        mView.setPresenter(mPresenter);
        mPresenter.setRepository(mRepository);

        mUserListener = new UserListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, mView)
                .commit();

    }

    @Override
    protected Toolbar getToolbar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        int a = 5;
        return tb;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setListener(mUserListener);
    }
}
