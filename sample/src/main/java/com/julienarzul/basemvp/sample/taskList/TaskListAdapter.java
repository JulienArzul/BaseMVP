package com.julienarzul.basemvp.sample.taskList;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.julienarzul.basemvp.sample.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2appaZ 2016
 */

class TaskListAdapter extends RecyclerView.Adapter<TaskListViewHolder> {

    private final TaskListContract.Presenter presenter;

    private final List<Task> taskList;

    TaskListAdapter(TaskListContract.Presenter presenter) {
        this.presenter = presenter;

        this.taskList = new ArrayList<>();
    }

    @Override
    public TaskListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TaskListViewHolder.newInstance(parent.getContext(), parent, this.presenter);
    }

    @Override
    public void onBindViewHolder(TaskListViewHolder holder, int position) {
        holder.update(this.taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.taskList.size();
    }

    void setTaskList(List<Task> taskList) {
        this.taskList.clear();

        this.taskList.addAll(taskList);

        this.notifyDataSetChanged();
    }
}
