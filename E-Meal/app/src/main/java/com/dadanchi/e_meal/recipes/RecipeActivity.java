package com.dadanchi.e_meal.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.HashSet;

public class RecipeActivity extends AppCompatActivity {

    private RecipeView mView;
    private RecipePresenter mPresenter;
    private RecipeRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle extras = getIntent().getExtras();
        HashSet<String> mProducts = (HashSet<String>) extras.get("products");

        mRepository = new RecipeRepository();
        mPresenter = new RecipePresenter(mProducts, mRepository);
        mView = RecipeView.create();
        mView.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_recipes, mView)
                .commit();
    }

    @Override
    protected void onResume() {
        mView.setPresenter(mPresenter);
        super.onResume();
    }
}
