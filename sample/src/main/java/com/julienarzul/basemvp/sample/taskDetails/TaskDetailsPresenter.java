package com.julienarzul.basemvp.sample.taskDetails;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.model.Task;

/**
 * Copyright @ Julien Arzul 2017
 */

class TaskDetailsPresenter extends BasePresenter<TaskDetailsContract.View> implements TaskDetailsContract.Presenter {

    private Task task;

    @Override
    public void setTask(Task task) {
        this.task = task;

        this.view.setTaskDescriptionText(task.taskDescription());
    }

    @Override
    public void onDeleteButtonClicked() {
        // TODO: call delete datasource
    }
}
