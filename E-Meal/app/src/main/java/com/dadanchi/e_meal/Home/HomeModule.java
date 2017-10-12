package com.dadanchi.e_meal.Home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 08/10/2017.
 */

@Module
public class HomeModule {
    @Provides
    HomeContracts.Presenter provideHomePresenter() {
        return new HomePresenter();
    }
}


