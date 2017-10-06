package com.dadanchi.e_meal.auth;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dadanchi.e_meal.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginView extends Fragment {


    private AuthPresenter mPresenter;

    public LoginView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login_view, container, false);

        mPresenter = new AuthPresenter(this.getActivity());

        Button loginButton = (Button) root.findViewById(R.id.btn_login);
        Button registerButton = (Button) root.findViewById(R.id.btn_register_form);
        final EditText emailInput = (EditText)root.findViewById(R.id.et_email);
        final EditText passwordInut = (EditText)root.findViewById(R.id.et_password);

        final LoginActivity activity = (LoginActivity) getActivity();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailInput.getText().toString();
                final String password = passwordInut.getText().toString();

                // validations
                Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                boolean emailMathes = emailPattern.matcher(email).matches();

                if (TextUtils.isEmpty(email.trim()) || !emailMathes) {
                    emailInput.setError("Invali email");
                } else if(TextUtils.isEmpty(password.trim()) || password.trim().length() < 5) {
                    passwordInut.setError("Password must be atleast 5 symbols");
                } else {
                    mPresenter.login(email, password);
                }

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });

        return root;
    }

    public static LoginView create() {
        return new LoginView();
    }
}
