package com.dadanchi.e_meal.products;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.ProductsRepository;

public class ProductsActivity extends BaseDrawerActivity {
    private ProductsContracts.Presenter mPresenter;
    private ProductsView mView;
    private ProductsRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_procucts);
        super.onCreate(savedInstanceState);

        mRepository = new ProductsRepository();
        mPresenter = new ProductsPresenter(mRepository);
        mView = ProductsView.create();
        mView.setPresenter(mPresenter);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_products, mView)
                .commit();
    }

    @Override
    protected Toolbar getToolbar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        int a = 5;
        return tb;
    }

    @Override
    protected void onResume() {
        mView.setPresenter(mPresenter);
        super.onResume();
    }
}
