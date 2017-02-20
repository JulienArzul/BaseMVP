package com.julienarzul.basemvp.sample.ui.taskList;

import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.model.Task;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2016
 */

interface TaskListContract {

    interface View extends MvpContract.View {

        void setTaskList(List<Task> data);

        void displayErrorView();

        void launchTaskDetailsScreen(Task task);

        void launchAddTaskScreen();
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void loadData();

        void onTaskItemClicked(Task clickedTask);

        void onAddTaskButtonClicked();
    }
}
