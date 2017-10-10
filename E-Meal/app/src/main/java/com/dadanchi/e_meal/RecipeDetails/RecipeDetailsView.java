package com.dadanchi.e_meal.RecipeDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.models.Recipe;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsView extends Fragment implements RecipeDetailsContracts.View{
    private RecipeDetailsContracts.Presenter mPresenter;
    private TextView mTitleView;
    private TextView mDescriptionView;
    private ImageView mImageView;

    public RecipeDetailsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_details_view, container, false);

        mTitleView = (TextView) root.findViewById(R.id.tv_recipe_title);
        mDescriptionView = (TextView) root.findViewById(R.id.tv_recipe_description);
        //mDescriptionView.setMovementMethod(new ScrollingMovementMethod());
        mImageView = (ImageView) root.findViewById(R.id.iv_recipe_image);

        return root;
    }

    @Override
    public void setPresenter(RecipeDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    public static RecipeDetailsView create() {
        return new RecipeDetailsView();
    }

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
    public void setItem(Recipe recipe) {
        mTitleView.setText(recipe.getTitle());
        mDescriptionView.setText(recipe.getDescription());
        // TODO -> make image loader class
        Picasso.with(getContext()).load(recipe.getImgUrl()).into(mImageView);
    }

}
