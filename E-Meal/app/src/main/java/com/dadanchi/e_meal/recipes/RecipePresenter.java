package com.dadanchi.e_meal.recipes;

import android.net.Uri;

import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        if(mView != null) {
            mView.showLoadingView();
        }
        io.reactivex.Observable<ArrayList<Recipe>> observable = mRepository.getAll();

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Recipe>>() {
                    @Override
                    public void accept(ArrayList<Recipe> recipes) throws Exception {
                        mView.setItems(recipes);
                        if(mView != null) {
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
    public void subscribe(RecipeContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }


    public void addRecipe(String title, String description, Uri uri) {
        mRepository.add(title, description, uri, mProducts);
    }
}
