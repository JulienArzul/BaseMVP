package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.storage.IStorageRepository;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

class TasksDatasource implements ITasksDatasource {

    private final IStorageRepository storageManager;

    private final List<Task> taskList;

    TasksDatasource(IStorageRepository storageManager) {
        this.storageManager = storageManager;

        this.taskList = this.storageManager.getTaskList();
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
