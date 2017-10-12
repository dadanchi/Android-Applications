package com.dadanchi.e_meal.profile;

import android.net.Uri;

import com.dadanchi.e_meal.base.BaseContracts;
import com.dadanchi.e_meal.repositories.AuthRepository;

/**
 * Created by dadanchi on 11/10/2017.
 */

public interface ProfileContracts {
    interface Presenter extends BaseContracts.Presenter<View> {
        void load();

        void setRepository(AuthRepository repository);

        void updateUser(Uri mUri);

    }

    interface View extends BaseContracts.View<Presenter> {

        void setUser(BaseContracts.User mUser);
    }
}
