package com.julienarzul.basemvp.sample.ui.addEditTask;

import com.julienarzul.basemvp.MvpContract;

/**
 * Copyright @ Julien Arzul 2017
 */

interface AddEditTaskContract {

    interface View extends MvpContract.View {

        void finishScreen();
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void onConfirmButtonClicked(String taskId, String taskDescription);
    }
}
