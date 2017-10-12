package com.dadanchi.e_meal.repositories;

import android.net.Uri;
import android.provider.ContactsContract;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.models.Recipe;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by dadanchi on 09/10/2017.
 */

public class RecipeRepository {
    private final DatabaseReference mData;
    private final StorageReference mStorage;

    @Inject
    public RecipeRepository() {
        mData = FirebaseDatabase.getInstance().getReference("recipes");
        mStorage = FirebaseStorage.getInstance().getReference("Images/Recipes");
    }

    public Observable<ArrayList<Recipe>> getAvailable(final HashSet<String> prods) {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<ArrayList<Recipe>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<ArrayList<Recipe>> e) throws Exception {
                final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                mData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(prods.size() != 0){
                            for (DataSnapshot recipe: dataSnapshot.getChildren()) {
                                HashMap<String, Object> currentRecipe =  (HashMap<String, Object>)recipe.getValue();
                                HashSet<String> products = new HashSet<String>();
                                if (currentRecipe.containsKey("products")) {
                                    products = new HashSet<String>(
                                            (ArrayList<String>) currentRecipe.get("products")
                                    );

                                    if(products.containsAll(prods)) {
                                        String name = (String) recipe.child("Name").getValue();
                                        String description = (String) recipe.child("Description").getValue();
                                        String imgUrl = (String) recipe.child("Image").getValue();

                                        recipes.add(new Recipe(name, description, imgUrl));
                                    }
                                }
                            }
                        }


                        e.onNext(recipes);
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

    public void add(final String title, final String description, Uri uri, final HashSet<String> products) {
        StorageReference filepath = mStorage.child(uri.getLastPathSegment());
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUri = taskSnapshot.getDownloadUrl();

                DatabaseReference newRecipe = mData.push();
                newRecipe.child("Name").setValue(title);
                newRecipe.child("Description").setValue(description);
                newRecipe.child("Image").setValue(downloadUri.toString());
                newRecipe.child("products").setValue(new ArrayList<String>(products));
            }
        });

    }

    public Observable<Recipe> getRecipe(final String name) {

        final DatabaseReference data = FirebaseDatabase.getInstance().getReference("recipes");
        return Observable.create(new ObservableOnSubscribe<Recipe>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Recipe> e) throws Exception {
                mData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot recipes) {
                        Recipe foundRecipe = new Recipe();

                        for(DataSnapshot recipe: recipes.getChildren()) {
                            String currentName = (String) recipe.child("Name").getValue();
                            if (currentName.compareTo(name) == 0) {
                                String description = (String) recipe.child("Description").getValue();
                                String imageUrl = (String) recipe.child("Image").getValue();
                                foundRecipe = new Recipe(name, description, imageUrl);
                            }
                        }

                        e.onNext(foundRecipe);
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
}
