package com.julienarzul.basemvp.sample.ui.main;

import com.julienarzul.basemvp.BasePresenter;

/**
 * Copyright @ Julien Arzul 2017
 */

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void onActivityBasedButtonClicked() {
        this.view.launchActivityBasedScreen();
    }

    @Override
    public void onFragmentBasedButtonClicked() {
        this.view.launchFragmentBasedScreen();
    }
}
