package com.dadanchi.e_meal.auth;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 12/10/2017.
 */

@Module
public class AuthModule {
    @Provides
    AuthContracts.Presenter provideAuthPresenter() {
        return new AuthPresenter();
    }
}
