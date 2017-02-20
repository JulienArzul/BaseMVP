package com.julienarzul.basemvp.sample.ui.taskList;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.datasource.DataCallback;
import com.julienarzul.basemvp.sample.datasource.ITasksDatasource;
import com.julienarzul.basemvp.sample.model.DatasourceError;
import com.julienarzul.basemvp.sample.model.Task;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

class TaskListPresenter extends BasePresenter<TaskListContract.View> implements TaskListContract.Presenter {

    private final ITasksDatasource tasksDatasource;

    TaskListPresenter(ITasksDatasource tasksDatasource) {
        this.tasksDatasource = tasksDatasource;
    }

    @Override
    public void loadData() {
        this.tasksDatasource.getTaskList(new TaskListDataCallback(this));
    }

    @Override
    public void onTaskItemClicked(Task clickedTask) {
        this.view.launchTaskDetailsScreen(clickedTask);
    }

    private void onGetTaskListSucceeded(List<Task> taskList) {
        if (this.view != null) {
            this.view.setTaskList(taskList);
        }
    }

    private void onGetTaskListFailed() {
        if (this.view != null) {
            this.view.displayErrorView();
        }
    }

    private static class TaskListDataCallback implements DataCallback<List<Task>> {

        private final WeakReference<TaskListPresenter> presenterWeak;

        private TaskListDataCallback(TaskListPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Task> data) {
            TaskListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetTaskListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            TaskListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetTaskListFailed();
            }
        }

    }
}
