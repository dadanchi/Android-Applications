package com.dadanchi.e_meal.Home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.UserListener;

public class HomeActivity extends AppCompatActivity {
    private HomePresenter mPresenter;
    private UserListener mUserListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeView view = HomeView.create();
        mPresenter = new HomePresenter(this);
        final Context context = this;

        mUserListener = new UserListener(this);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, view)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setListener(mUserListener);
    }
}
