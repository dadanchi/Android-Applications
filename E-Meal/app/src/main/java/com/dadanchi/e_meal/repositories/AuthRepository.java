package com.dadanchi.e_meal.repositories;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.models.FBUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class AuthRepository {
    private final DatabaseReference mUsersData;
    private final StorageReference mStorage;
    private FirebaseAuth mRepository;
    private FirebaseUser mUser;

    @Inject
    public AuthRepository() {
        mRepository = FirebaseAuth.getInstance();
        mUser = mRepository.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference("Images/Users");
        mUsersData = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public String getUsername() {
        if(mRepository.getCurrentUser() != null) {
            return mRepository.getCurrentUser().getDisplayName();
        }

        return "";
    }

    public Observable<BaseContracts.User> getCurrentUser() {
        return Observable.create(new ObservableOnSubscribe<BaseContracts.User>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<BaseContracts.User> e) throws Exception {
                DatabaseReference dataRef = mUsersData.child(mRepository.getCurrentUser().getUid()).getRef();
                dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String imageUrl = dataSnapshot.child("Image").getValue(String.class);
                        BaseContracts.User user = new FBUser(
                                mRepository.getCurrentUser().getUid(),
                                mRepository.getCurrentUser().getEmail(),
                                mRepository.getCurrentUser().getDisplayName(),
                                imageUrl);


                        e.onNext(user);
                        e.onComplete();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    public Observable<Boolean> SignUpWithEmail(final String email, final String password, final String firstName,
                                               final String lastName, final Uri uri) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<Boolean> e) throws Exception {
                mRepository.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mUser = mRepository.getCurrentUser();
                                String displayName = firstName + " " + lastName;
                                updateUser(displayName, uri);

                                e.onNext(true);
                            } else {
                                e.onNext(false);
                            }

                            e.onComplete();
                        }
                    });
            }
        });
    }

    public void logout() {
        mRepository.signOut();
    }

    private void updateUser(String displayName, Uri uri) {
        if (mUser != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build();

            mUser.updateProfile(profileUpdates);
            final DatabaseReference userRef = mUsersData.child(mUser.getUid());

            if (uri != null) {
                StorageReference filepath = mStorage.child(mUser.getUid());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        userRef.child("Image").setValue(downloadUri.toString());
                    }
                });
            }

            userRef.child("username").setValue(displayName);
        }
    }

    public void addListener(UserListener userListener) {
        mRepository.addAuthStateListener(userListener.getListener());
    }

    public Observable<Boolean> login(final String email, final String password) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<Boolean> e) throws Exception {
                mRepository.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mUser = mRepository.getCurrentUser();
                                e.onNext(true);
                            } else {
                                e.onNext(false);
                            }

                            e.onComplete();
                        }
                    });
            }
        });

    }

    public void updateUserImage(Uri uri) {
        StorageReference storageReference = mStorage.child(mRepository.getCurrentUser().getUid());

        StorageReference filepath = storageReference.child(uri.getLastPathSegment());
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUri = taskSnapshot.getDownloadUrl();

                DatabaseReference userRef = mUsersData.child(mRepository.getCurrentUser().getUid());
                userRef.child("Image").setValue(downloadUri.toString());
            }
        });
    }
}
