package com.julienarzul.basemvp.sample.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Copyright @ Julien Arzul 2016
 */
@AutoValue
public abstract class Task implements Parcelable {

    public static Task create(int taskId, String taskDescription) {
        return new AutoValue_Task(taskId, taskDescription);
    }

    abstract int taskId();

    public abstract String taskDescription();
}
