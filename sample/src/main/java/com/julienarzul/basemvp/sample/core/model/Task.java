package com.julienarzul.basemvp.sample.core.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Copyright @ Julien Arzul 2016
 */
@AutoValue
public abstract class Task implements Parcelable {

    public static Task create(Integer taskId, String taskDescription) {
        if (taskId == null) {
            return null;
        }

        return new AutoValue_Task(taskId, taskDescription);
    }

    public abstract int taskId();

    public abstract String taskDescription();
}
