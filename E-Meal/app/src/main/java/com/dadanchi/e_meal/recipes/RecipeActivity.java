package com.dadanchi.e_meal.recipes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.HashSet;

import javax.inject.Inject;

public class RecipeActivity extends BaseDrawerActivity {
    @Inject
    RecipeContracts.Presenter mPresenter;
    @Inject
    RecipeRepository mRepository;
    private RecipeView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recipe);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        HashSet<String> mProducts = (HashSet<String>) extras.get("products");

        mPresenter = new RecipePresenter();
        mPresenter.setRepository(mRepository);
        mPresenter.setProducts(mProducts);

        mView = RecipeView.create();
        mView.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_recipes, mView)
                .commit();
    }

    @Override
    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onResume() {
        mView.setPresenter(mPresenter);
        mPresenter.load();
        super.onResume();
    }
}
