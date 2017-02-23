package com.julienarzul.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

/**
 * Base class to extend if you're creating a DialogFragment as a View.
 *
 * It takes care of storing the view's presenter and handling fragment's lifecycle to attach or detach the view from the presenter.
 *
 * Copyright @ Julien Arzul 2016
 */

public abstract class MvpDialogFragment<T extends MvpContract.Presenter> extends DialogFragment implements MvpContract.View {

    /**
     * The Presenter attached to this View
     */
    protected T presenter;

    /**
     * Must be overriden to define the Presenter used by this fragment.
     *
     * @return The presenter that will be used by this View.
     */
    protected abstract T createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = this.createPresenter();
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }

        return super.getLayoutInflater(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.presenter != null) {
            this.presenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        this.presenter = null;
    }
}
