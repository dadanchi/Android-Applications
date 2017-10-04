package com.dadanchi.e_meal.Home;

import com.dadanchi.e_meal.base.BaseContracts;

/**
 * Created by dadanchi on 04/10/2017.
 */

public interface HomeContracts {
    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {
    }
}
