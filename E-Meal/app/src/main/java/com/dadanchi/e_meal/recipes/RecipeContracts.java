package com.dadanchi.e_meal.recipes;

import com.dadanchi.e_meal.base.BaseContracts;
/**
 * Created by dadanchi on 09/10/2017.
 */

public interface RecipeContracts {
    interface View extends BaseContracts.View<RecipeContracts.Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<RecipeContracts.View> {

        void load();

    }
}