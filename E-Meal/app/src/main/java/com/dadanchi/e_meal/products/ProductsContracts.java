package com.dadanchi.e_meal.products;

import com.dadanchi.e_meal.base.BaseContracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by dadanchi on 07/10/2017.
 */

public interface ProductsContracts {

    interface View extends BaseContracts.View<ProductsContracts.Presenter> {

        void setItems(HashMap<String, ArrayList<String>> products, ArrayList<String> categories);
        void showLoadingView();
        void hideLoadingView();
    }

    interface Presenter extends BaseContracts.Presenter<ProductsContracts.View> {

        void load();

        boolean toggleProduct(String text);

        HashSet<String> getAddedProducts();
    }
}
