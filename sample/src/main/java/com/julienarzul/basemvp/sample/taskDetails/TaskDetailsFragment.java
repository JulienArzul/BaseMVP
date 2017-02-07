package com.julienarzul.basemvp.sample.taskDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julienarzul.basemvp.MvpFragment;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.model.Task;

/**
 * Created by Julien Arzul on 7/2/17.
 * Copyright @ ZenPark 2017
 */

public class TaskDetailsFragment extends MvpFragment<TaskDetailsContract.Presenter> implements TaskDetailsContract.View {

    private static final String TASK_BUNDLE_KEY = "com.julienarzul.basemvp.sample.taskDetails.TaskDetailsFragment.task";
    private TextView taskDescriptionTextView;

    public static TaskDetailsFragment newInstance(Task task) {
        Bundle args = new Bundle();
        args.putParcelable(TASK_BUNDLE_KEY, task);

        TaskDetailsFragment fragment = new TaskDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TaskDetailsContract.Presenter createPresenter() {
        return new TaskDetailsPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Task task = null;

        Bundle arguments = this.getArguments();
        if (arguments.containsKey(TASK_BUNDLE_KEY)) {
            task = arguments.getParcelable(TASK_BUNDLE_KEY);
        }

        if (task == null) {
            throw new IllegalArgumentException("Fragment must be created with a task!");
        }

        this.presenter.setTask(task);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);

        this.taskDescriptionTextView = (TextView) view.findViewById(R.id.task_details_task_description_textview);
        view.findViewById(R.id.task_details_delete_task_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDeleteButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void setTaskDescriptionText(String taskDescription) {
        this.taskDescriptionTextView.setText(taskDescription);
    }
}