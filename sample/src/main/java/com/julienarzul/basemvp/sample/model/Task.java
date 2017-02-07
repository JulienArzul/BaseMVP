package com.julienarzul.basemvp.sample.model;

/**
 * Copyright @ Julien Arzul 2016
 */

public class Task {

    private final int taskId;

    private final String taskDescription;

    public Task(int taskId, String taskDescription) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
    }

    public String taskDescription() {
        return taskDescription;
    }
}
