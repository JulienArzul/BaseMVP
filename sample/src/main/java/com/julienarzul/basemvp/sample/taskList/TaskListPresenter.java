package com.julienarzul.basemvp.sample.taskList;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.datasource.ITasksDatasource;
import com.julienarzul.basemvp.sample.model.Task;

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
        this.tasksDatasource.getTaskList(new ITasksDatasource.DataCallback<List<Task>>() {
            @Override
            public void onDataLoaded(List<Task> data) {
                view.setTaskList(data);
            }

            @Override
            public void onDataNotAvailable() {
                //TODO: Handle empty state
            }
        });
    }
}
