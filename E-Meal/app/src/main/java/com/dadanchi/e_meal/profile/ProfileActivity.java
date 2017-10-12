package com.dadanchi.e_meal.profile;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.AuthRepository;

import javax.inject.Inject;

public class ProfileActivity extends BaseDrawerActivity {
    @Inject
    ProfileContracts.Presenter mPresenter;
    @Inject
    AuthRepository mRepository;
    private ProfileView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        mView = ProfileView.create();

        mPresenter.setRepository(mRepository);
        mView.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_profile, mView)
                .commit();
    }



    @Override
    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }
}
