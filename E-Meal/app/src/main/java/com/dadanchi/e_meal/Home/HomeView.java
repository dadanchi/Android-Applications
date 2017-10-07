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
import com.dadanchi.e_meal.auth.RegisterActivity;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.products.ProductsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeView extends Fragment {


    public HomeView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_view, container, false);
        final AuthPresenter presenter = new AuthPresenter(getActivity());

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
}
