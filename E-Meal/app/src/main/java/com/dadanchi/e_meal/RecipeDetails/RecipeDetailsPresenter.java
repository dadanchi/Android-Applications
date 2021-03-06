package com.dadanchi.e_meal.RecipeDetails;

import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dadanchi on 10/10/2017.
 */

public class RecipeDetailsPresenter implements RecipeDetailsContracts.Presenter {
    private RecipeDetailsContracts.View mView;
    private RecipeRepository mRepository;
    private String mTitle;


    public RecipeDetailsPresenter() {
    }

    @Override
    public void subscribe(RecipeDetailsContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void load() {
        if (mView != null) {
            mView.showLoadingView();
        }
        mRepository.getRecipe(mTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Recipe>() {
                    @Override
                    public void accept(Recipe recipe) throws Exception {
                        mView.setItem(recipe);
                        if (mView != null) {
                            mView.hideLoadingView();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public void setRepository(RecipeRepository repository) {
        mRepository = repository;
    }
}
