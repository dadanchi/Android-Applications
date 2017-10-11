package com.dadanchi.e_meal.recipes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.recipes.utils.RecipeRecyclerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeView extends Fragment implements RecipeContracts.View {
    private RecipeContracts.Presenter mPresenter;
    private RecyclerView mRecylerView;
    private ArrayList<Recipe> mRecipes;
    private RecipeRecyclerAdapter mAdapter;
    private AVLoadingIndicatorView mLoadingView;

    public RecipeView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_view, container, false);

        mRecylerView = (RecyclerView) root.findViewById(R.id.rv_recipes);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLoadingView = (AVLoadingIndicatorView) root.findViewById(R.id.avi);

        mRecipes = new ArrayList<>();

        mAdapter = new RecipeRecyclerAdapter(mRecipes, this);

        mRecylerView.setAdapter(mAdapter);

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

    @Override
    public void setItems(ArrayList<Recipe> recipes) {
        mAdapter.clear();
        mAdapter.addAll(recipes);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingView() {
        mLoadingView.show();
    }

    @Override
    public void hideLoadingView() {
        mLoadingView.hide();
    }
}
