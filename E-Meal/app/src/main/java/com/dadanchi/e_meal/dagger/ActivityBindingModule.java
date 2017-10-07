package com.dadanchi.e_meal.dagger;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.Home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by dadanchi on 08/10/2017.
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
