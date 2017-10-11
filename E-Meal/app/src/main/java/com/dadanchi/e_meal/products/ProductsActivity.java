package com.dadanchi.e_meal.products;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.ProductsRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class ProductsActivity extends BaseDrawerActivity {
    @Inject
    ProductsContracts.Presenter mPresenter;
    @Inject
    @Named("ProductsRepository")
    BaseContracts.Repository mRepository;

    private ProductsView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_procucts);
        super.onCreate(savedInstanceState);

        mPresenter.setRepository(mRepository);
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
