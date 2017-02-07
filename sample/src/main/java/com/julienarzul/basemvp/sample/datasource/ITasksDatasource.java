package com.julienarzul.basemvp.sample.datasource;

import com.julienarzul.basemvp.sample.model.Task;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

public interface ITasksDatasource {

    void getTaskList(DataCallback<List<Task>> callback);

    void deleteTask(Task task, DataCallback<Void> callback);
}
