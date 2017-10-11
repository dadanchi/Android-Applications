package com.dadanchi.e_meal.products;

import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.ProductsRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dadanchi on 11/10/2017.
 */

@Module
public class ProductsModule {
    @Provides
    ProductsContracts.Presenter provideProductsPresenter() {

        return new ProductsPresenter();
    }
}
