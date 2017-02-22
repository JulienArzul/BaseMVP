package com.julienarzul.basemvp.sample.core.repositories.storage;

import com.julienarzul.basemvp.sample.core.model.Task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
public class FileStorageRepositoryTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File storageFolder;

    private FileStorageRepository testedStorageRepository;

    @Before
    public void setUp() throws Exception {
        this.storageFolder = temporaryFolder.newFolder();

        this.testedStorageRepository = new FileStorageRepository(new JsonFileStorageManager(this.storageFolder));
    }

    @After
    public void tearDown() throws Exception {
        this.storageFolder.delete();
    }

    @Test
    public void testGetInitialTaskList() throws Exception {
        List<Task> taskList = this.testedStorageRepository.getTaskList();

        Assert.assertEquals("Task list should be empty when we never updated it", new ArrayList<>(), taskList);
    }

    @Test
    public void testUpdateTaskList() throws Exception {
        List<Task> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newList.add(Task.create(i, "Task " + i));
        }

        this.testedStorageRepository.updateTaskList(newList);

        List<Task> refreshedTaskList = this.testedStorageRepository.getTaskList();
        Assert.assertEquals(newList, refreshedTaskList);
    }
}