package com.julienarzul.basemvp;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2AppaZ 2016
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
