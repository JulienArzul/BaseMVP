package com.julienarzul.basemvp.sample.core.repositories.storage;

import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.entities.JsonTask;
import com.julienarzul.basemvp.sample.core.repositories.mappers.TaskMapper;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class FileStorageRepository implements IStorageRepository {

    private static final String TASK_LIST_KEY = "task_list";

    private final JsonFileStorageManager fileStorageManager;

    public FileStorageRepository(JsonFileStorageManager fileStorageManager) {
        this.fileStorageManager = fileStorageManager;
    }

    @Override
    public List<Task> getTaskList() {
        return this.fileStorageManager.readObjectList(TASK_LIST_KEY, JsonTask.class, TaskMapper.regularMapper());
    }

    @Override
    public void updateTaskList(List<Task> taskList) {
        this.fileStorageManager.writeObjectList(TASK_LIST_KEY, taskList, JsonTask.class, TaskMapper.inverseMapper());
    }
}
