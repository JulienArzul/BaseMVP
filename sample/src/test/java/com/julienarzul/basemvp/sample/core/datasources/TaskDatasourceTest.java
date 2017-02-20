package com.julienarzul.basemvp.sample.core.datasources;

import android.support.annotation.NonNull;

import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.storage.IStorageRepository;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskDatasourceTest {

    private final TasksDatasource testedTaskDatasource;

    private final IStorageRepository mockStorageRepository;

    public TaskDatasourceTest() {
        this.mockStorageRepository = Mockito.mock(IStorageRepository.class);
        List<Task> taskList = createTaskList();
        Mockito.when(this.mockStorageRepository.getTaskList()).thenReturn(taskList);

        this.testedTaskDatasource = new TasksDatasource(mockStorageRepository);
    }

    @NonNull
    private static List<Task> createTaskList() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            taskList.add(createTaskForIndex(i));
        }

        return taskList;
    }

    private static Task createTaskForIndex(int i) {
        return Task.create(i, "Task " + (i + 1));
    }

    @Test
    public void testGetTaskListSuccess() throws Exception {
        DataCallback<List<Task>> mockCallback = Mockito.mock(DataCallback.class);

        this.testedTaskDatasource.getTaskList(mockCallback);

        Mockito.verify(mockCallback).onDataLoaded(createTaskList());
    }

    @Test
    public void testDeleteTaskSuccess() throws Exception {
        DataCallback<Void> mockCallback = Mockito.mock(DataCallback.class);

        this.testedTaskDatasource.deleteTask(createTaskForIndex(4), mockCallback);

        Mockito.verify(mockCallback).onDataLoaded(null);
        Mockito.verify(this.mockStorageRepository).updateTaskList(Mockito.<Task>anyList());
    }

    @Test
    public void testDeleteTaskFail() throws Exception {
        DataCallback<Void> mockCallback = Mockito.mock(DataCallback.class);

        this.testedTaskDatasource.deleteTask(createTaskForIndex(-1), mockCallback);

        Mockito.verify(mockCallback).onDataError(Mockito.any(DatasourceError.class));
    }
}
