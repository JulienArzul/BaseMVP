package com.julienarzul.basemvp.sample.taskList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.model.Task;

/**
 * Created by Julien Arzul on 14/10/2016.
 * Copyright @ 2appaZ 2016
 */

class TaskListViewHolder extends RecyclerView.ViewHolder {

    private final TaskListContract.Presenter presenter;

    private TextView textView;

    private TaskListViewHolder(View itemView, TaskListContract.Presenter presenter) {
        super(itemView);

        this.presenter = presenter;

        textView = (TextView) itemView.findViewById(R.id.task_item_textview);
    }

    static TaskListViewHolder newInstance(Context context, ViewGroup parent, TaskListContract.Presenter presenter) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);

        return new TaskListViewHolder(itemView, presenter);
    }

    void update(Task item) {
        this.textView.setText(item.taskDescription());
    }
}
