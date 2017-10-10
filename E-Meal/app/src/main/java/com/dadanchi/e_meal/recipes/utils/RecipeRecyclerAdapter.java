package com.dadanchi.e_meal.recipes.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.models.Recipe;
import com.dadanchi.e_meal.recipes.RecipeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dadanchi on 10/10/2017.
 */

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.RecipeHolder> {

    public static class RecipeHolder extends RecyclerView.ViewHolder {
        private View mView;

        public RecipeHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView titleView =  (TextView) mView.findViewById(R.id.tv_recipe_title);
            titleView.setText(title);
        }

        public  void setImage(Context context, String ImgUrl) {
            ImageView imageView = (ImageView) mView.findViewById(R.id.iv_recipe);
            Picasso.with(context).load(ImgUrl).into(imageView);
        }
    }

    private final Context mContext;
    private ArrayList<Recipe> mRecipes;

    public RecipeRecyclerAdapter(ArrayList<Recipe> recipes, Context context) {
        mRecipes = recipes;
        mContext = context;
    }

    @Override
    public RecipeRecyclerAdapter.RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row, parent, false);

        return new RecipeRecyclerAdapter.RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerAdapter.RecipeHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);

        holder.setTitle(recipe.getTitle());
        holder.setImage(mContext ,recipe.getImgUrl());
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void clear() {
        mRecipes = new ArrayList<>();
    }

    public void addAll(ArrayList<Recipe> recipes) {
        mRecipes = recipes;
    }
}