package com.dadanchi.e_meal.profile;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 11/10/2017.
 */

@Module
public class ProfileModule {
    @Provides
    ProfileContracts.Presenter provideProfilePresenter() {
        return new ProfilePresenter();
    }
}
