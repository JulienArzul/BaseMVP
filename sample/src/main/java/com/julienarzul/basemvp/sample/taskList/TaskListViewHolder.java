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
 * Copyright @ Julien Arzul 2016
 */

class TaskListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TaskListContract.Presenter presenter;

    private TextView textView;

    private Task currentItem;

    private TaskListViewHolder(View itemView, TaskListContract.Presenter presenter) {
        super(itemView);

        this.presenter = presenter;

        itemView.setOnClickListener(this);
        textView = (TextView) itemView.findViewById(R.id.task_item_textview);
    }

    static TaskListViewHolder newInstance(Context context, ViewGroup parent, TaskListContract.Presenter presenter) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);

        return new TaskListViewHolder(itemView, presenter);
    }

    void update(Task item) {
        this.currentItem = item;

        this.textView.setText(item.taskDescription());
    }

    @Override
    public void onClick(View v) {
        if (this.itemView.equals(v)) {
            if (this.currentItem != null) {
                this.presenter.onTaskItemClicked(this.currentItem);
            }
        }
    }
}
