package com.dadanchi.e_meal.products;

import com.dadanchi.e_meal.base.BaseContracts;

/**
 * Created by dadanchi on 07/10/2017.
 */

public interface ProductsContracts {

    interface View extends BaseContracts.View<ProductsContracts.Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<ProductsContracts.View> {

        void load();
    }}
