package com.dadanchi.e_meal.profile;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseContracts;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.net.URI;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileView extends Fragment implements ProfileContracts.View{
    private ProfileContracts.Presenter mPresenter;
    private ImageButton mProfileImage;
    private TextView mName;
    private TextView mEmail;
    private Button mSaveChangesButton;
    private Uri mUri;
    private AVLoadingIndicatorView mLoadingView;

    public ProfileView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_view, container, false);

        mProfileImage = (ImageButton) root.findViewById(R.id.ib_profile_image);
        mName = (TextView) root.findViewById(R.id.tv_name);
        mEmail = (TextView) root.findViewById(R.id.tv_email);
        mSaveChangesButton = (Button) root.findViewById(R.id.btn_save_changes);
        mLoadingView = (AVLoadingIndicatorView) root.findViewById(R.id.avi);

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        mSaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUri != null) {
                    mPresenter.updateUser(mUri);
                    Toast.makeText(getContext(), "Image updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "You haven't selected a new image", Toast.LENGTH_SHORT).show();
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
            //mProfileImage.setImageURI(mPresenter.getUserProfileImage());
        }
    }

    public static ProfileView create() {
        return new ProfileView();
    }

    @Override
    public void setPresenter(ProfileContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setUser(BaseContracts.User user) {
        mName.setText(user.getName());
        mEmail.setText(user.getEmail());
        Picasso.with(getContext()).load(user.getProfileImage()).into(mProfileImage);;
    }

    @Override
    public void onResume() {
        mPresenter.subscribe(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        mPresenter = null;
        super.onDestroy();
    }
}
