package com.julienarzul.basemvp.sample.ui.addEditTask;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.core.datasources.DataCallback;
import com.julienarzul.basemvp.sample.core.datasources.ITasksDatasource;
import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Task;
import com.julienarzul.basemvp.sample.core.utils.TextUtils;

import java.lang.ref.WeakReference;

/**
 * Copyright @ Julien Arzul 2017
 */

class AddEditTaskPresenter extends BasePresenter<AddEditTaskContract.View> implements AddEditTaskContract.Presenter {

    private final ITasksDatasource tasksDatasource;

    AddEditTaskPresenter(ITasksDatasource tasksDatasource) {
        this.tasksDatasource = tasksDatasource;
    }

    @Override
    public void onConfirmButtonClicked(String taskId, String taskDescription) {
        boolean isInputValid = this.isIdValid(taskId);
        isInputValid = isInputValid && this.isDescriptionValid(taskDescription);

        if (isInputValid) {
            final Task taskToAdd = Task.create(Integer.valueOf(taskId), taskDescription);
            this.tasksDatasource.addTask(taskToAdd, new AddTaskDataCallback(taskToAdd, this));
        }
    }

    private boolean isIdValid(String taskId) {
        try {
            Integer.parseInt(taskId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDescriptionValid(String taskDescription) {
        return !TextUtils.isEmpty(taskDescription);
    }

    private void onAddTaskSucceeded(Task taskAdded) {
        if (this.view != null) {
            this.view.finishScreen();
        }
    }

    private void onAddTaskFailed(DatasourceError error) {
        if (this.view != null) {
            // TODO
        }
    }

    private static class AddTaskDataCallback implements DataCallback<Void> {

        private final Task taskAdded;
        private final WeakReference<AddEditTaskPresenter> presenterWeak;

        private AddTaskDataCallback(Task taskAdded, AddEditTaskPresenter presenter) {
            this.taskAdded = taskAdded;
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(Void data) {
            AddEditTaskPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onAddTaskSucceeded(this.taskAdded);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            AddEditTaskPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onAddTaskFailed(error);
            }
        }
    }
}
