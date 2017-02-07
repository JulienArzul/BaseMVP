package com.julienarzul.basemvp.sample.datasource;

import com.julienarzul.basemvp.sample.model.DatasourceError;
import com.julienarzul.basemvp.sample.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

class TasksDatasource implements ITasksDatasource {

    private static volatile TasksDatasource instance;

    private final List<Task> taskList;

    private TasksDatasource() {
        this.taskList = new ArrayList<>(createTaskList());
    }

    static TasksDatasource getInstance() {
        if (instance == null) {
            synchronized (TasksDatasource.class) {
                if (instance == null) {
                    instance = new TasksDatasource();
                }
            }
        }

        return instance;
    }

    private static List<Task> createTaskList() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            taskList.add(new Task(i, "Task " + (i + 1)));
        }

        return taskList;
    }

    @Override
    public void getTaskList(DataCallback<List<Task>> callback) {
        callback.onDataLoaded(this.taskList);
    }

    @Override
    public void deleteTask(Task task, DataCallback<Void> callback) {
        boolean removed = this.taskList.remove(task);

        if (removed) {
            callback.onDataLoaded(null);
        } else {
            callback.onDataError(new DatasourceError());
        }
    }
}
