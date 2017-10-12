package com.dadanchi.e_meal.auth.register;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.auth.AuthContracts;

import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterView extends Fragment implements AuthContracts.View{

    private AuthContracts.Presenter mPresenter;
    private ImageButton mProfileImage;
    private EditText mEmailInput;
    private EditText mFirstNameInput;
    private EditText mPasswordInut;
    private EditText mLastNameInput;
    private Uri mUri;

    public RegisterView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register_view, container, false);

        Button registerButton = (Button) root.findViewById(R.id.btn_register);

        mEmailInput = (EditText)root.findViewById(R.id.et_email);
        mFirstNameInput = (EditText) root.findViewById(R.id.et_firstName);
        mLastNameInput = (EditText) root.findViewById(R.id.et_lastName);
        mPasswordInut = (EditText)root.findViewById(R.id.et_password);
        mProfileImage = (ImageButton) root.findViewById(R.id.ib_profile_image);

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmailInput.getText().toString();
                final String firstName = mFirstNameInput.getText().toString();
                final String lastName = mLastNameInput.getText().toString();
                final String password = mPasswordInut.getText().toString();

                // validations
                Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Pattern namePattern = Pattern.compile("[A-Z][a-z\\-]+");
                boolean emailMathes = emailPattern.matcher(email).matches();
                boolean firstNameMatches = namePattern.matcher(firstName.trim()).matches();
                boolean lastNameMatches = namePattern.matcher(lastName.trim()).matches();

                if (TextUtils.isEmpty(email.trim()) || !emailMathes) {
                    mEmailInput.setError("Invalid email");
                } else if(TextUtils.isEmpty(password.trim()) || password.trim().length() < 5) {
                    mPasswordInut.setError("Password must be atleast 5 symbols");
                } else if(TextUtils.isEmpty(firstName.trim())) {
                    mFirstNameInput.setError("Incorrect username");
                } else if(!firstNameMatches) {
                    mFirstNameInput.setError("Name must start with capital letter");
                } else if(TextUtils.isEmpty(lastName.trim())) {
                    mLastNameInput.setError("Incorrect username");
                } else if(!lastNameMatches) {
                    mLastNameInput.setError("Name must start with capital letter");
                } else {
                    mPresenter.register(email, password, firstName, lastName, mUri);
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            mUri = data.getData();
            mProfileImage.setImageURI(mUri);
        }
    }


    public static RegisterView create() {
        return new RegisterView();
    }

    @Override
    public void setPresenter(AuthContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
    }

    @Override
    public void isUserIn(Boolean isRegistered) {
        if (isRegistered) {
            Toast.makeText(getContext(), "Greetengs, " + mPresenter.getCurrentUsername(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Email is taken", Toast.LENGTH_SHORT).show();
        }
    }
}
