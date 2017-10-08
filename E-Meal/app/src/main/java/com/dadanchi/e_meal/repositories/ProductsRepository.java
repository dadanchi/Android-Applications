package com.dadanchi.e_meal.repositories;

import android.app.Activity;
import android.content.Intent;
import android.database.Observable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by dadanchi on 07/10/2017.
 */

public class ProductsRepository {
    private final DatabaseReference mData;
    private final Activity mcontext;
    private FirebaseAuth mRepository;

    public ProductsRepository(Activity context) {
        mRepository = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference().child("Products");
        mcontext = context;
    }

    // get data logic

    public io.reactivex.Observable<HashMap<String, ArrayList<String>>> getProducts() {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<HashMap<String, ArrayList<String>>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<HashMap<String, ArrayList<String>>> e) throws Exception {

                final HashMap<String, ArrayList<String>> products = new HashMap<>();
                mData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot productType: dataSnapshot.getChildren()) {

                            ArrayList<String> currentProducts = new ArrayList<>();
                            String category = (String) productType.getKey();

                            for (DataSnapshot product: productType.getChildren()) {
                                String name =  (String) product.child("name").getValue();

                                currentProducts.add(name);
                            }

                            products.put(category, currentProducts);
                        }
                        e.onNext(products);
                        e.onComplete();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        mData.removeEventListener(this);
                    }
                });
            }
        });
    }

    //
    //
    // initial stuff
    public void addProducts() {
        for (Product[] products: initialProducts) {
            String category = products[0].getCategory().getName();
            List<Product> newProducts = new ArrayList<Product>(Arrays.asList(products));
            mData.child(category).setValue(newProducts);
            //mData.child(category).setValue(products);

        }
    }

    private Product[][] initialProducts = new Product[][] {
            // Vegies
            new Product[]  {
                    new Product("Potato", Category.Vegetable),
                    new Product("Tomato", Category.Vegetable),
                    new Product("Pepper", Category.Vegetable),
                    new Product("Garlic", Category.Vegetable),
                    new Product("Cucumber", Category.Vegetable),
                    new Product("Hot pepper", Category.Vegetable),
                    new Product("Corn", Category.Vegetable),
                    new Product("Carrot", Category.Vegetable),
                    new Product("Spinach", Category.Vegetable),
                    new Product("Beans", Category.Vegetable),
                    new Product("Peas", Category.Vegetable),
                    new Product("Onion", Category.Vegetable),
                    new Product("Cabbage", Category.Vegetable),
                    new Product("Mushroom", Category.Vegetable),
            },
            // Fruits
            new Product[]{
                    new Product("Orange", Category.Fruit),
                    new Product("Grapefruit", Category.Fruit),
                    new Product("Lemon", Category.Fruit),
                    new Product("Lime", Category.Fruit),
                    new Product("Apple", Category.Fruit),
                    new Product("Peach", Category.Fruit),
                    new Product("Apricot", Category.Fruit),
                    new Product("Kiwi", Category.Fruit),
                    new Product("Banana", Category.Fruit),
                    new Product("Pineapple", Category.Fruit),
                    new Product("Pear", Category.Fruit),
                    new Product("Cherry", Category.Fruit),
                    new Product("Watermelon", Category.Fruit),
                    new Product("Sour cherry", Category.Fruit),
                    new Product("Raspberry", Category.Fruit),
                    new Product("Strawberry", Category.Fruit),
                    new Product("Grape", Category.Fruit),
                    new Product("Bluebarry", Category.Fruit),
                    new Product("Quince", Category.Fruit),
                    new Product("Avocado", Category.Fruit),
                    new Product("Coconut", Category.Fruit),
                    new Product("Plum", Category.Fruit),
                    new Product("Mango", Category.Fruit),
            },
            // Meat
            new Product[]{
                    new Product("Pork", Category.Meat),
                    new Product("Chicken", Category.Meat),
                    new Product("Duck", Category.Meat),
                    new Product("Lamb", Category.Meat),
                    new Product("Beef", Category.Meat),
                    new Product("Ham", Category.Meat),
                    new Product("Salami", Category.Meat),
                    new Product("Sausage", Category.Meat),
                    new Product("Bacon", Category.Meat),
                    new Product("Peperoni", Category.Meat),
            },
            // Dairy
            new Product[]{

                    new Product("Milk", Category.Dairy),
                    new Product("Eggs", Category.Dairy),
                    new Product("Butter", Category.Dairy),
                    new Product("Cheese", Category.Dairy),
                    new Product("Margarine", Category.Dairy),
                    new Product("Soft cheese", Category.Dairy),
                    new Product("Yoghurt", Category.Dairy),
                    new Product("Cream", Category.Dairy),
                    new Product("Potato", Category.Dairy),
            },
            // Sauce
            new Product[]{

                    new Product("Ketchup", Category.Sauce),
                    new Product("BBQ", Category.Sauce),
                    new Product("Mayo", Category.Sauce),
                    new Product("Chili", Category.Sauce),
                    new Product("Tomato sauce", Category.Sauce),
                    new Product("Mustard", Category.Sauce),
                    new Product("Tartar", Category.Sauce),
                    new Product("Cesar", Category.Sauce),
                    new Product("Sweet sour", Category.Sauce),
                    new Product("Garlic", Category.Sauce),
            },
            new Product[]{

                    // Sweets
                    new Product("Sugar", Category.Sweets),
                    new Product("Ice cream", Category.Sweets),
                    new Product("Comfiture", Category.Sweets),
                    new Product("Biscuits", Category.Sweets),
                    new Product("Milk chocolate", Category.Sweets),
                    new Product("Black chocolate", Category.Sweets),
                    new Product("Cocoa", Category.Sweets),
            },
            new Product[]{
                    // Herbs
                    new Product("Mint", Category.Herbs),
                    new Product("Black pepper", Category.Herbs),
                    new Product("Red pepper", Category.Herbs),
                    new Product("Cinnamon", Category.Herbs),
                    new Product("Ginger", Category.Herbs),
                    new Product("Rosemary", Category.Herbs),
                    new Product("Saffron", Category.Herbs),
                    new Product("Basil", Category.Herbs),
                    new Product("Sault", Category.Herbs),
            }
    };

    private enum Category {
        Vegetable ("Vegetable"),
        Fruit ("Fruit"),
        Meat ("Meat"),
        Dairy ("Dairy"),
        Sauce ("Sauce"),
        Sweets ("Sweets"),
        Herbs ("Herbs and Spices");

        private String name;

        private Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private class Product {
        private final String name;
        private final Category category;

        public Product(String name, Category category) {
            this.name = name;
            this.category = category;
        }

        public String getName() {
            return this.name;
        }

        public Category getCategory() {
            return this.category;
        }
    }
}
