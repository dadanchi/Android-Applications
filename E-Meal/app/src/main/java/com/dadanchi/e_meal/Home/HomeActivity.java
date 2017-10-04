package com.dadanchi.e_meal.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeView view = HomeView.create();


        // TODO -> make an auth service for that
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, view)
                    .commit();
        } else {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }


    }
}
