package com.julienarzul.basemvp;

/**
 * Copyright @ Julien Arzul 2016
 */

public interface MvpContract {

    /**
     * Represents a View in MVP.
     */
    interface View {

    }

    /**
     * Represents a Presenter in MVP.
     * <p>
     * By default, it can attach and detach its View.
     *
     * @param <T> The type of the View that the Presenter will handle
     */
    interface Presenter<T extends View> {

        void attachView(T view);

        void detachView();
    }
}
