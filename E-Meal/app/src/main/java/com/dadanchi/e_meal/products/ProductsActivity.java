package com.dadanchi.e_meal.products;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.repositories.ProductsRepository;

public class ProductsActivity extends AppCompatActivity {
    private ProductsContracts.Presenter mPresenter;
    private ProductsView mView;
    private ProductsRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procucts);

        mRepository = new ProductsRepository(this);
        mPresenter = new ProductsPresenter(mRepository);
        mView = ProductsView.create();
        mView.setPresenter(mPresenter);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_products, mView)
                .commit();
    }
}
