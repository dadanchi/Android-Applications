package com.dadanchi.e_meal.recipes;

import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.HashSet;

/**
 * Created by dadanchi on 09/10/2017.
 */

public class RecipePresenter implements RecipeContracts.Presenter {
    private final HashSet<String> mProducts;
    private final RecipeRepository mRepository;
    private RecipeContracts.View mView;

    public RecipePresenter(HashSet<String> products, RecipeRepository repository) {
        mProducts = products;
        mRepository = repository;
    }

    @Override
    public void load() {

    }

    @Override
    public void subscribe(RecipeContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
