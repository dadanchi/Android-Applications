package com.dadanchi.e_meal.auth;

import android.net.Uri;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;

/**
 * Created by dadanchi on 07/10/2017.
 */

public interface AuthContracts {
    interface View extends BaseContracts.View<AuthContracts.Presenter> {

        void isUserIn(Boolean isRegistered);
    }

    interface Presenter extends BaseContracts.Presenter<AuthContracts.View> {

        void login(String email, String password);

        void setRepository(AuthRepository mRepository);

        void register(String email, String password, String firstName, String lastName, Uri uri);

        String getCurrentUsername();
    }
}
