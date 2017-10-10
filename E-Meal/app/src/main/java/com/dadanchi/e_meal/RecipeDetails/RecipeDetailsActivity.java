package com.dadanchi.e_meal.RecipeDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.RecipeRepository;

public class RecipeDetailsActivity extends AppCompatActivity {
    private RecipeDetailsView mView;
    private RecipeDetailsContracts.Presenter mPresenter;
    private RecipeRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        mView = RecipeDetailsView.create();
        mRepository = new RecipeRepository();
        mPresenter = new RecipeDetailsPresenter(title, mRepository);

        mView.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_recipe_details, mView)
                .commit();
    }
}
