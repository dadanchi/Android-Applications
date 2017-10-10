package com.dadanchi.e_meal.repositories;

import android.net.Uri;

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


    public RecipeRepository() {
        mData = FirebaseDatabase.getInstance().getReference("recipes");
        mStorage = FirebaseStorage.getInstance().getReference("Images/Recipes");
    }

    public Observable<ArrayList<Recipe>> getAll() {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<ArrayList<Recipe>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<ArrayList<Recipe>> e) throws Exception {
                final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                mData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot recipe: dataSnapshot.getChildren()) {

                                String name = (String) recipe.child("Name").getValue();
                                String description = (String) recipe.child("Description").getValue();
                                String imgUrl = (String) recipe.child("Image").getValue();

                                recipes.add(new Recipe(name, description, imgUrl));
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

    // initial adding
}
