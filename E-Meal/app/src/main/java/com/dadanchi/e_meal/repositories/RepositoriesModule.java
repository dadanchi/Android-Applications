package com.dadanchi.e_meal.repositories;

import com.dadanchi.e_meal.base.BaseContracts;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 11/10/2017.
 */

@Module
public class RepositoriesModule {
    @Provides
    @Named("ProductsRepository")
    BaseContracts.Repository provideProductsRepository() {
        return new ProductsRepository();
    }
}
