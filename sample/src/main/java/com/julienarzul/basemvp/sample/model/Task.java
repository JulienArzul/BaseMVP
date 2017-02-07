package com.julienarzul.basemvp.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright @ Julien Arzul 2016
 */

public class Task implements Parcelable {

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    private final int taskId;
    private final String taskDescription;

    public Task(int taskId, String taskDescription) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
    }

    private Task(Parcel in) {
        this.taskId = in.readInt();
        this.taskDescription = in.readString();
    }

    public String taskDescription() {
        return taskDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.taskId);
        dest.writeString(this.taskDescription);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskDescription != null ? taskDescription.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskId != task.taskId) return false;
        return taskDescription != null ? taskDescription.equals(task.taskDescription) : task.taskDescription == null;

    }
}
