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

/**
 * Created by dadanchi on 05/10/2017.
 */

public class AuthRepository {
    private final DatabaseReference mUsersData;
    private final DatabaseReference mTakenUsernamesData;
    private final Activity mContext;
    private FirebaseAuth mRepository;
    private FirebaseUser mUser;

    public AuthRepository(Activity context) {
        // fix
        mRepository = FirebaseAuth.getInstance();
        mUser = mRepository.getCurrentUser();
        mContext = context;

        mUsersData = FirebaseDatabase.getInstance().getReference().child("Users");
        mTakenUsernamesData = FirebaseDatabase.getInstance().getReference().child("Taken Usernames");
    }

    public BaseContracts.User getCurrentUser() {
        BaseContracts.User user = new FBUser(mRepository.getCurrentUser());

        return user;
    }

    public void SignUpWithEmail(String email, String password, final String username) {
        mRepository.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            mUser = mRepository.getCurrentUser();
                            updateUser(username);

                            Intent intent = new Intent(mContext, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(intent);

                        } else {
                            Toast.makeText(mContext, "Email already exists", Toast.LENGTH_SHORT).show();
                        }
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
            DatabaseReference takenNamesData = mTakenUsernamesData;
            userRef.child("username").setValue(displayName);
            mTakenUsernamesData.child(displayName).setValue(displayName);
        }
    }

    public void addListener(UserListener userListener) {
        mRepository.addAuthStateListener(userListener.getListener());
    }

    public void login(String email, String password) {
        mRepository.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mUser = mRepository.getCurrentUser();
                            Toast.makeText(mContext, "Welcome " + mUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(mContext, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(intent);
                        } else {
                            Toast.makeText(mContext, "Wrong email/password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean doesUserExist(final String username) {
        final boolean[] result = {false};
        mTakenUsernamesData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    if (data.child(username).exists()) {
                        result[0] = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return (result[0]);
    }
}
