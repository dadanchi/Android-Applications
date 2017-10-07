package com.dadanchi.e_meal.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by dadanchi on 08/10/2017.
 */

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
