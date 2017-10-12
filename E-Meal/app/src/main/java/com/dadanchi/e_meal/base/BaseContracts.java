package com.dadanchi.e_meal.base;

import android.net.Uri;

import io.reactivex.Observable;

/**
 * Created by dadanchi on 04/10/2017.
 */

public abstract class BaseContracts {
    public interface User {
        String getEmail();
        String getId();
        String getName();
        String getProfileImage();
    }

    public interface View<T extends Presenter> {
        void setPresenter(T presenter);
    }

    public interface Presenter<T extends View> {
        void subscribe(T view);
        void unsubscribe();
    }

    public interface Repository<T> {
        Observable<T> getAll();

        void add();
    }
}
