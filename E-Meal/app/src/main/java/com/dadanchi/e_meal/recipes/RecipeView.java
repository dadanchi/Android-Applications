package com.dadanchi.e_meal.recipes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dadanchi.e_meal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeView extends Fragment implements RecipeContracts.View {


    private RecipeContracts.Presenter mPresenter;

    public RecipeView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_view, container, false);

        return root;
    }

    public static RecipeView create() {
        return new RecipeView();
    }

    @Override
    public void setPresenter(RecipeContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        mPresenter.subscribe(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        mPresenter = null;
        super.onDestroy();
    }
}
