package com.dadanchi.e_meal.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dadanchi.e_meal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthView extends Fragment {


    public AuthView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth_view, container, false);
    }

    public static AuthView create() {
        return new AuthView();
    }
}
