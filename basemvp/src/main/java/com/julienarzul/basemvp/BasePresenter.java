package com.julienarzul.basemvp;

/**
 * Base class to extend if you're creating a Presenter.
 *
 * It takes care of attaching and detaching the view.
 *
 * Copyright @ Julien Arzul 2016
 */

public abstract class BasePresenter<T extends MvpContract.View> implements MvpContract.Presenter<T> {

    /**
     * The View linked to this Presenter
     */
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
