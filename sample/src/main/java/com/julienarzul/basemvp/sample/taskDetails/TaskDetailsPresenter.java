package com.julienarzul.basemvp.sample.taskDetails;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.datasource.DataCallback;
import com.julienarzul.basemvp.sample.datasource.ITasksDatasource;
import com.julienarzul.basemvp.sample.model.DatasourceError;
import com.julienarzul.basemvp.sample.model.Task;

import java.lang.ref.WeakReference;

/**
 * Copyright @ Julien Arzul 2017
 */

class TaskDetailsPresenter extends BasePresenter<TaskDetailsContract.View> implements TaskDetailsContract.Presenter {

    private final ITasksDatasource tasksDatasource;

    private Task task;

    TaskDetailsPresenter(ITasksDatasource tasksDatasource) {
        this.tasksDatasource = tasksDatasource;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;

        this.view.setTaskDescriptionText(task.taskDescription());
    }

    @Override
    public void onDeleteButtonClicked() {
        this.tasksDatasource.deleteTask(this.task, new DeleteTaskDataCallback(this));
    }

    private void onDeleteTaskSucceeded() {
        if (this.view != null) {
            this.view.displayDeleteConfirmationToast();
            this.view.finishScreen();
        }
    }

    private void onDeleteTaskFailed() {
        if (this.view != null) {
            this.view.displayErrorDialog();
        }
    }

    private static class DeleteTaskDataCallback implements DataCallback<Void> {

        private final WeakReference<TaskDetailsPresenter> presenterWeak;

        private DeleteTaskDataCallback(TaskDetailsPresenter taskDetailsPresenter) {
            this.presenterWeak = new WeakReference<>(taskDetailsPresenter);
        }

        @Override
        public void onDataLoaded(Void data) {
            TaskDetailsPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onDeleteTaskSucceeded();
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            TaskDetailsPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onDeleteTaskFailed();
            }
        }
    }
}
