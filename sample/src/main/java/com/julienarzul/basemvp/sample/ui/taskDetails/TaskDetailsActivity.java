package com.julienarzul.basemvp.sample.ui.taskDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.model.Task;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String TASK_EXTRA_KEY = "com.julienarzul.basemvp.sample.ui.taskDetails.TaskDetailsActivity.task";

    public static Intent getStartingIntent(Context context, @NonNull Task task) {
        Intent intent = new Intent(context, TaskDetailsActivity.class);

        intent.putExtra(TASK_EXTRA_KEY, task);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_task_details);

        if (savedInstanceState == null) {
            Intent intent = this.getIntent();
            if (intent.hasExtra(TASK_EXTRA_KEY)) {
                Task task = intent.getParcelableExtra(TASK_EXTRA_KEY);

                this.getSupportFragmentManager().beginTransaction()
                        .add(R.id.task_details_container, TaskDetailsFragment.newInstance(task))
                        .commit();
            } else {
                throw new IllegalArgumentException("Activity must be started with a task!");
            }
        }
    }
}
