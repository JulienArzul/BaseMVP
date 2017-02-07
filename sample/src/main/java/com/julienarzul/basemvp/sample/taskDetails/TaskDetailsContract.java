package com.julienarzul.basemvp.sample.taskDetails;

import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.model.Task;

/**
 * Copyright @ Julien Arzul 2017
 */

interface TaskDetailsContract {

    interface View extends MvpContract.View {

        void setTaskDescriptionText(String taskDescription);

        void displayDeleteConfirmationToast();

        void displayErrorDialog();

        void finishScreen();
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void setTask(Task task);

        void onDeleteButtonClicked();
    }
}
