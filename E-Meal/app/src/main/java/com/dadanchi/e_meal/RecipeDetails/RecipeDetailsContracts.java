package com.dadanchi.e_meal.RecipeDetails;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.repositories.RecipeRepository;

/**
 * Created by dadanchi on 10/10/2017.
 */

public interface RecipeDetailsContracts {
    public interface Presenter extends BaseContracts.Presenter<View> {
        void load();

        void setTitle(String title);

        void setRepository(RecipeRepository repository);
    }

    public interface View extends BaseContracts.View<Presenter> {
        void setItem(Recipe recipe);
        void showLoadingView();
        void hideLoadingView();
    }
}
