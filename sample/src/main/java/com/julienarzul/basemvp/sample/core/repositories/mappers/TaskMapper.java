package com.julienarzul.basemvp.sample.core.repositories.mappers;

import com.julienarzul.basemvp.sample.core.mappers.Mapper;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.repositories.entities.JsonTask;

/**
 * Copyright @ Julien Arzul 2017
 */
public class TaskMapper {

    public static Mapper<JsonTask, Task> regularMapper() {
        return new RegularMapper();
    }

    public static Mapper<Task, JsonTask> inverseMapper() {
        return new InverseMapper();
    }

    private static class RegularMapper implements Mapper<JsonTask, Task> {

        @Override
        public Task map(JsonTask source) {
            if (source == null) {
                return null;
            }

            return Task.create(source.id, source.description);
        }
    }

    private static class InverseMapper implements Mapper<Task, JsonTask> {

        @Override
        public JsonTask map(Task source) {
            if (source == null) {
                return null;
            }

            JsonTask jsonTask = new JsonTask();
            jsonTask.id = source.taskId();
            jsonTask.description = source.taskDescription();
            return jsonTask;
        }
    }
}
