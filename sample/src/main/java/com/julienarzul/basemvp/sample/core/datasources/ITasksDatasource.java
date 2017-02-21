package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.Task;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

public interface ITasksDatasource {

    void addTask(Task task, DataCallback<Void> callback);

    void getTaskList(DataCallback<List<Task>> callback);

    void deleteTask(Task task, DataCallback<Void> callback);
}
