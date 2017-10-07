package com.dadanchi.e_meal.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dadanchi.e_meal.R;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterView extends Fragment {

    private AuthPresenter mPresenter;

    public RegisterView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register_view, container, false);
        mPresenter = new AuthPresenter(this.getActivity());

        Button registerButton = (Button) root.findViewById(R.id.btn_register);

        final EditText emailInput = (EditText)root.findViewById(R.id.et_email);
        final EditText firstNameInput = (EditText) root.findViewById(R.id.et_firstName);
        final EditText lastNameInput = (EditText) root.findViewById(R.id.et_lastName);
        final EditText passwordInut = (EditText)root.findViewById(R.id.et_password);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailInput.getText().toString();
                final String firstName = firstNameInput.getText().toString();
                final String lastName = lastNameInput.getText().toString();
                final String password = passwordInut.getText().toString();

                // validations
                Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Pattern namePattern = Pattern.compile("[A-Z][a-z\\-]+");
                boolean emailMathes = emailPattern.matcher(email).matches();
                boolean firstNameMatches = namePattern.matcher(firstName.trim()).matches();
                boolean lastNameMatches = namePattern.matcher(lastName.trim()).matches();

                if (TextUtils.isEmpty(email.trim()) || !emailMathes) {
                    emailInput.setError("Invalid email");
                } else if(TextUtils.isEmpty(password.trim()) || password.trim().length() < 5) {
                    passwordInut.setError("Password must be atleast 5 symbols");
                } else if(TextUtils.isEmpty(firstName.trim())) {
                    firstNameInput.setError("Incorrect username");
                } else if(!firstNameMatches) {
                    firstNameInput.setError("Name must start with capital letter");
                } else if(TextUtils.isEmpty(lastName.trim())) {
                    lastNameInput.setError("Incorrect username");
                } else if(!lastNameMatches) {
                    lastNameInput.setError("Name must start with capital letter");
                } else {
                    mPresenter.register(email, password, firstName, lastName);
                }
            }
        });

        return root;
    }

    public static RegisterView create() {
        return new RegisterView();
    }
}
