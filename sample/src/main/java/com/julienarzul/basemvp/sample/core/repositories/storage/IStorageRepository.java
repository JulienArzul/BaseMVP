package com.julienarzul.basemvp.sample.core.repositories.storage;

import com.julienarzul.basemvp.sample.core.model.Task;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public interface IStorageRepository {

    List<Task> getTaskList();

    void updateTaskList(List<Task> taskList);
}
