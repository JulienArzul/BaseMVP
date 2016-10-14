package com.julienarzul.basemvp;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2AppaZ 2016
 */

public interface MvpContract {

    interface View {

    }

    interface Presenter<T extends View> {

        void attachView(T view);

        void detachView();
    }
}
