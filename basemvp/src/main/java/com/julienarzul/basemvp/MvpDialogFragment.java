package com.julienarzul.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2AppaZ 2016
 */

public abstract class MvpDialogFragment<T extends MvpContract.Presenter> extends DialogFragment implements MvpContract.View {

    protected T presenter;

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
