package com.dadanchi.e_meal.RecipeDetails;


import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 11/10/2017.
 */

@Module
public class RecipeDetailsModule {
    @Provides
    RecipeDetailsContracts.Presenter provideProductsPresenter() {
        return new RecipeDetailsPresenter();
    }

}
