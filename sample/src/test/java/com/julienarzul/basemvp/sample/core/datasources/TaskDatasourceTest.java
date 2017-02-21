package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.storage.FileStorageRepository;
import com.julienarzul.basemvp.sample.core.repositories.storage.JsonFileStorageManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskDatasourceTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File storageFolder;

    private TasksDatasource testedTaskDatasource;
    private List<Task> initialTaskList;
    private FileStorageRepository storageManager;

    @Before
    public void setUp() throws Exception {
        this.storageFolder = temporaryFolder.newFolder();

        this.initialTaskList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            this.initialTaskList.add(Task.create(i, "Task " + i));
        }

        this.storageManager = new FileStorageRepository(new JsonFileStorageManager(this.storageFolder));
        this.storageManager.updateTaskList(this.initialTaskList);

        this.testedTaskDatasource = new TasksDatasource(this.storageManager);
    }

    @After
    public void tearDown() throws Exception {
        this.storageManager.updateTaskList(new ArrayList<Task>());

        this.storageFolder.delete();
    }

    @Test
    public void testGetTaskListSuccess() throws Exception {
        DataCallback<List<Task>> mockCallback = Mockito.mock(DataCallback.class);

        this.testedTaskDatasource.getTaskList(mockCallback);

        Mockito.verify(mockCallback).onDataLoaded(this.initialTaskList);
    }

    @Test
    public void testDeleteTaskSuccess() throws Exception {
        final int initialTaskListSize = this.initialTaskList.size();
        Task deletedTask = this.initialTaskList.get(initialTaskListSize > 1 ? initialTaskListSize / 2 : 0);

        // Actually deletes the task
        DataCallback<Void> mockCallback = Mockito.mock(DataCallback.class);
        this.testedTaskDatasource.deleteTask(deletedTask, mockCallback);
        Mockito.verify(mockCallback).onDataLoaded(null);

        // Retrieves the new list of task to test its new value
        DataCallback<List<Task>> mockGetListCallback = Mockito.mock(DataCallback.class);
        this.testedTaskDatasource.getTaskList(mockGetListCallback);

        ArgumentCaptor<List<Task>> taskListCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(mockGetListCallback).onDataLoaded(taskListCaptor.capture());
        List<Task> refreshedTaskList = taskListCaptor.getValue();

        // New list must have one less element and not contain the task we just deleted
        Assert.assertEquals(initialTaskListSize - 1, refreshedTaskList.size());
        Assert.assertFalse(refreshedTaskList.contains(deletedTask));
    }

    @Test
    public void testDeleteTaskFail() throws Exception {
        DataCallback<Void> mockCallback = Mockito.mock(DataCallback.class);

        this.testedTaskDatasource.deleteTask(Task.create(-99, "Unknown Task"), mockCallback);

        Mockito.verify(mockCallback).onDataError(Mockito.any(DatasourceError.class));

        DataCallback<List<Task>> mockGetListCallback = Mockito.mock(DataCallback.class);
        this.testedTaskDatasource.getTaskList(mockGetListCallback);
        Mockito.verify(mockGetListCallback).onDataLoaded(this.initialTaskList);
    }
}
