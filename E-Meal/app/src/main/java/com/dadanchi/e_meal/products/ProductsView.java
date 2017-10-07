package com.dadanchi.e_meal.products;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dadanchi.e_meal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsView extends Fragment {


    private ProductsPresenter mPresenter;

    public ProductsView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_products_view, container, false);

        mPresenter = new ProductsPresenter(getActivity());

        Button add = (Button) root.findViewById(R.id.btn_addPr);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.InitAdd();
            }
        });

        return root;
    }

    public static ProductsView create() {
        return new ProductsView();
    }

}
