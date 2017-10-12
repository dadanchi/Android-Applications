package com.dadanchi.e_meal.recipes;

import android.net.Uri;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dadanchi on 09/10/2017.
 */

public interface RecipeContracts {
    interface View extends BaseContracts.View<RecipeContracts.Presenter> {
        void setItems(ArrayList<Recipe> recipes);

        void showLoadingView();
        void hideLoadingView();
    }

    interface Presenter extends BaseContracts.Presenter<RecipeContracts.View> {
        void load();

        void setProducts(HashSet<String> products);

        void setRepository(RecipeRepository repository);

        void addRecipe(String title, String description, Uri uri);
    }
}
