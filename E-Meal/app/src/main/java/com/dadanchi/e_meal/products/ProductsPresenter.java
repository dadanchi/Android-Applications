package com.dadanchi.e_meal.products;

import com.dadanchi.e_meal.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dadanchi on 07/10/2017.
 */

public class ProductsPresenter implements ProductsContracts.Presenter {
    private ProductsContracts.View mView;
    private ProductsRepository mreposiroty;
    private HashSet<String> mAddedProducts;

    public  ProductsPresenter(ProductsRepository repository) {
        mreposiroty = repository;
        mAddedProducts = new HashSet<>();
    }

    public void InitAdd() {
        mreposiroty.addProducts();
    }


    public void load() {
        if (mView != null) {
            mView.showLoadingView();
        }
        io.reactivex.Observable<HashMap<String, ArrayList<String>>> observable = mreposiroty.getProducts();

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HashMap<String, ArrayList<String>>>() {
                    @Override
                    public void accept(HashMap<String, ArrayList<String>> products) throws Exception {
                        ArrayList<String> categories = new ArrayList<String>(products.keySet().size());
                        for(String key: products.keySet()) {
                            categories.add(key);
                        }
                        mView.setItems(products, categories);
                        if (mView != null) {
                            mView.hideLoadingView();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public HashSet<String> getAddedProducts() {
        return mAddedProducts;
    }

    @Override
    public boolean toggleProduct(String product) {
        if (mAddedProducts.contains(product)) {
            mAddedProducts.remove(product);
            return false;
        } else {
            mAddedProducts.add(product);
            return true;
        }
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
