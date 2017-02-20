package com.julienarzul.basemvp.sample.core.repositories.mappers;

import com.julienarzul.basemvp.sample.core.mappers.Mapper;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.entities.JsonTask;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskMapperTest {

    @Test
    public void testRegularMapperWhenSourceIsNull() {
        Mapper<JsonTask, Task> testedMapper = TaskMapper.regularMapper();

        Task result = testedMapper.map(null);

        Assert.assertNull(result);
    }

    @Test
    public void testRegularMapperWhenSourceIsComplete() {
        Mapper<JsonTask, Task> testedMapper = TaskMapper.regularMapper();

        Integer taskId = 1;
        String taskDescription = "Task 1";

        JsonTask sourceJsonTask = new JsonTask();
        sourceJsonTask.id = taskId;
        sourceJsonTask.description = taskDescription;

        Task result = testedMapper.map(sourceJsonTask);

        Assert.assertEquals(Task.create(taskId, taskDescription), result);
    }

    @Test
    public void testRegularMapperWithNullId() {
        Mapper<JsonTask, Task> testedMapper = TaskMapper.regularMapper();

        Integer taskId = null;
        String taskDescription = "Task 1";

        JsonTask sourceJsonTask = new JsonTask();
        sourceJsonTask.id = taskId;
        sourceJsonTask.description = taskDescription;

        Task result = testedMapper.map(sourceJsonTask);

        Assert.assertNull(result);
    }

    @Test
    public void testInverseMapperWhenSourceIsNull() {
        Mapper<Task, JsonTask> testedMapper = TaskMapper.inverseMapper();

        JsonTask result = testedMapper.map(null);

        Assert.assertNull(result);
    }

    @Test
    public void testInverseMapperWhenSourceIsComplete() {
        Mapper<Task, JsonTask> testedMapper = TaskMapper.inverseMapper();

        Integer taskId = 1;
        String taskDescription = "Task 1";

        Task source = Task.create(taskId, taskDescription);
        JsonTask result = testedMapper.map(source);

        Assert.assertEquals(taskId, result.id);
        Assert.assertEquals(taskDescription, result.description);
    }
}
