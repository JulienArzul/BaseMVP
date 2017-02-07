package com.julienarzul.basemvp;

/**
 * Copyright @ Julien Arzul 2016
 */

public abstract class BasePresenter<T extends MvpContract.View> implements MvpContract.Presenter<T> {

    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
