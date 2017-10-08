package com.dadanchi.e_meal.products;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dadanchi.e_meal.R;

import java.util.ArrayList;
import java.util.HashMap;
import com.dadanchi.e_meal.utils.ExpandableListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsView extends Fragment implements ProductsContracts.View {
    private ProductsContracts.Presenter mPresenter;
    private ExpandableListAdapter mAdapter;
    private ArrayList<String> mCategories;
    private HashMap<String, ArrayList<String>> mProducts;
    private ExpandableListView mExpListView;

    public ProductsView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_products_view, container, false);

        mExpListView = (ExpandableListView) root.findViewById(R.id.elv_products);

        mProducts = new HashMap<String, ArrayList<String>>();
        mCategories = new ArrayList<String>();

        mAdapter = new ExpandableListAdapter(mProducts, mCategories, getContext());
        mExpListView.setAdapter(mAdapter);

        // go away
//        Button add = (Button) root.findViewById(R.id.btn_addPr);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //mPresenter.InitAdd();
//                // TODO -> refactor
//            }
//        });

        mExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String text = (String) mAdapter.getChild(groupPosition, childPosition);

                // TODO -> change UI
                if(mPresenter.toggleProduct(text)) {
                    Toast.makeText(getContext(), text + " added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), text + " removed!", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        return root;
    }

    public static ProductsView create() {
        return new ProductsView();
    }

    @Override
    public void setPresenter(ProductsContracts.Presenter presenter) {
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
    public void setItems(HashMap<String, ArrayList<String>> products, ArrayList<String> categories) {
        mAdapter.clear();
        mAdapter.addAll(products, categories);
        mAdapter.notifyDataSetChanged();
    }
}
