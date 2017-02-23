package com.julienarzul.basemvp.sample.ui.main;

import com.julienarzul.basemvp.MvpContract;

/**
 * Copyright @ Julien Arzul 2017
 */

interface MainContract {

    interface View extends MvpContract.View {

        void launchActivityBasedScreen();

        void launchFragmentBasedScreen();
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void onActivityBasedButtonClicked();

        void onFragmentBasedButtonClicked();
    }
}
