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
        public Task map(JsonTask model) {
            if (model == null) {
                return null;
            }

            return Task.create(model.id, model.description);
        }
    }

    private static class InverseMapper implements Mapper<Task, JsonTask> {

        @Override
        public JsonTask map(Task model) {
            if (model == null) {
                return null;
            }

            JsonTask jsonTask = new JsonTask();
            jsonTask.id = model.taskId();
            jsonTask.description = model.taskDescription();
            return jsonTask;
        }
    }
}
