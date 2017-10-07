package com.dadanchi.e_meal.Home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.register.RegisterActivity;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.products.ProductsActivity;
import com.dadanchi.e_meal.repositories.AuthRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeView extends Fragment implements HomeContracts.View {
    HomeContracts.Presenter mPresenter;

    public HomeView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_view, container, false);

        // TODO -> MOVE IT
        final AuthPresenter presenter = new AuthPresenter();
        presenter.setRepository(new AuthRepository(this.getActivity()));

        Button productsButton = (Button) root.findViewById(R.id.btn_products);
        Button logoutBtn = (Button) root.findViewById(R.id.btn_logout);

        final Context context = getContext();

        productsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO -> MOVE IT
                presenter.logout();

                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public static HomeView create() {
        return new HomeView();
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
    public void setPresenter(HomeContracts.Presenter presenter) {
        mPresenter = presenter;
    }
}
