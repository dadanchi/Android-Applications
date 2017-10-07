package com.dadanchi.e_meal;

import com.dadanchi.e_meal.dagger.AppComponent;
import com.dadanchi.e_meal.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by dadanchi on 08/10/2017.
 */

public class EmealApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
