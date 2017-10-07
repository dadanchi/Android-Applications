package com.dadanchi.e_meal.products;

import android.app.Activity;
import android.database.Observable;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dadanchi on 07/10/2017.
 */

public class ProductsPresenter implements ProductsContracts.Presenter {
    private ProductsContracts.View mView;
    private ProductsRepository mreposiroty;

    public  ProductsPresenter(ProductsRepository repository) {
        mreposiroty = repository;
    }

    public void InitAdd() {
        mreposiroty.addProducts();
    }


    public void load() {
        mreposiroty.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, ArrayList<String>>>() {
                    @Override
                    public void accept(Map<String, ArrayList<String>> products) throws Exception {
                        // view.setProducts(products)
                    }
                });
    }

    @Override
    public void subscribe(ProductsContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
