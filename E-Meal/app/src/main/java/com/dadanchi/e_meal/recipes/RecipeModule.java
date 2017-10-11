package com.dadanchi.e_meal.recipes;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 11/10/2017.
 */

@Module
public class RecipeModule {
    @Provides
    RecipeContracts.Presenter provideProductsPresenter() {
        return new RecipePresenter();
    }
}
