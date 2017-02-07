package com.julienarzul.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright @ Julien Arzul 2016
 */

public abstract class MvpActivity<T extends MvpContract.Presenter> extends AppCompatActivity implements MvpContract.View {

    protected T presenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = this.createPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.presenter != null) {
            this.presenter.detachView();
        }
        this.presenter = null;
    }
}
