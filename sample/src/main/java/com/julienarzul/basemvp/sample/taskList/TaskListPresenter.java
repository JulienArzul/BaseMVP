package com.julienarzul.basemvp.sample.taskList;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.datasource.ITasksDatasource;
import com.julienarzul.basemvp.sample.model.Task;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2appaZ 2016
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

    private void onTaskListLoaded(List<Task> taskList) {
        if (this.view != null) {
            this.view.setTaskList(taskList);
        }
    }

    private void onTaskListEmpty() {
        if (this.view != null) {
            //TODO: Handle empty state
        }
    }

    private static class TaskListDataCallback implements ITasksDatasource.DataCallback<List<Task>> {

        private final WeakReference<TaskListPresenter> presenterWeak;

        private TaskListDataCallback(TaskListPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Task> data) {
            TaskListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onTaskListLoaded(data);
            }
        }

        @Override
        public void onDataNotAvailable() {
            TaskListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onTaskListEmpty();
            }
        }

    }
}
