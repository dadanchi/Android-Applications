package com.dadanchi.e_meal.products;

import android.app.Activity;

import com.dadanchi.e_meal.repositories.ProductsRepository;

/**
 * Created by dadanchi on 07/10/2017.
 */

public class ProductsPresenter {
    private final Activity mcontext;
    private ProductsRepository mreposiroty;

    public  ProductsPresenter(Activity context) {
        mcontext = context;
        mreposiroty = new ProductsRepository();
    }

    public void InitAdd() {
        mreposiroty.addProducts();
    }
}
