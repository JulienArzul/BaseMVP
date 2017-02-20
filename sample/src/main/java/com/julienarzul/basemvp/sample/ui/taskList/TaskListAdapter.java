package com.julienarzul.basemvp.sample.ui.taskList;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.julienarzul.basemvp.sample.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
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
        // Calculates difference
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(this.taskList, taskList));

        // Updates list
        this.taskList.clear();
        this.taskList.addAll(taskList);

        // Notify adapter
        diffResult.dispatchUpdatesTo(this);
    }

    private class DiffUtilCallback extends DiffUtil.Callback {

        private final List<Task> oldList;
        private final List<Task> newList;

        private DiffUtilCallback(List<Task> oldList, List<Task> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return this.oldList.size();
        }

        @Override
        public int getNewListSize() {
            return this.newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return this.oldList.get(oldItemPosition) == this.newList.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return this.oldList.get(oldItemPosition).equals(this.newList.get(newItemPosition));
        }
    }
}
