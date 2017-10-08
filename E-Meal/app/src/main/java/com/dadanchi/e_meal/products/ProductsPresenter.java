package com.dadanchi.e_meal.products;

import com.dadanchi.e_meal.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.HashMap;
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
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
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
