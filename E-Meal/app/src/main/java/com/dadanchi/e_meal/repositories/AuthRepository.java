package com.dadanchi.e_meal.repositories;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.models.FBUser;
import com.google.android.gms.tasks.OnCompleteListener;
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

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by dadanchi on 05/10/2017.
 */

public class AuthRepository {
    private final DatabaseReference mUsersData;
    private FirebaseAuth mRepository;
    private FirebaseUser mUser;

    @Inject
    public AuthRepository() {
        mRepository = FirebaseAuth.getInstance();
        mUser = mRepository.getCurrentUser();

        mUsersData = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public BaseContracts.User getCurrentUser() {
        BaseContracts.User user = new FBUser(
                mRepository.getCurrentUser().getUid(),
                mRepository.getCurrentUser().getEmail(),
                mRepository.getCurrentUser().getDisplayName());

        return user;
    }

    public Observable<Boolean> SignUpWithEmail(final String email, final String password, final String firstName, final String lastName) {
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
                                    updateUser(displayName);

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

    private void updateUser(String displayName) {
        if (mUser != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName).build();
            mUser.updateProfile(profileUpdates);

            DatabaseReference userRef = mUsersData.child(mUser.getUid());
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
}
