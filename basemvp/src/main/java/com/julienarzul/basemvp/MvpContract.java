package com.julienarzul.basemvp;

/**
 * Copyright @ Julien Arzul 2016
 */

public interface MvpContract {

    interface View {

    }

    interface Presenter<T extends View> {

        void attachView(T view);

        void detachView();
    }
}
