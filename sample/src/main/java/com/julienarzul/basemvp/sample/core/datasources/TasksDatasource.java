package com.julienarzul.basemvp.sample.core.datasources;

import android.support.annotation.NonNull;

import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.storage.IStorageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

class TasksDatasource implements ITasksDatasource {

    private final IStorageRepository storageManager;

    private final List<Task> taskList;

    TasksDatasource(IStorageRepository storageManager) {
        this.storageManager = storageManager;

        this.taskList = new ArrayList<>();
        List<Task> storedTaskList = this.storageManager.getTaskList();

        if (storedTaskList == null) {
            storedTaskList = createTaskList();
            this.updateTaskListStored();
        }

        this.taskList.addAll(storedTaskList);
    }

    @NonNull
    private static List<Task> createTaskList() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            taskList.add(Task.create(i, "Task " + (i + 1)));
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
            this.updateTaskListStored();
        } else {
            callback.onDataError(new DatasourceError());
        }
    }

    private void updateTaskListStored() {
        this.storageManager.updateTaskList(this.taskList);
    }
}
