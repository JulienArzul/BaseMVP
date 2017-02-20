package com.julienarzul.basemvp.sample.core.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskTest {

    @Test
    public void testTaskCreation() {
        int taskId = 1;
        String taskDescription = "Task 1";

        Task task = Task.create(taskId, taskDescription);

        Assert.assertEquals(taskId, task.taskId());
        Assert.assertEquals(taskDescription, task.taskDescription());

        // Can't create task with a null id
        Assert.assertNull(Task.create(null, taskDescription));
    }
}
