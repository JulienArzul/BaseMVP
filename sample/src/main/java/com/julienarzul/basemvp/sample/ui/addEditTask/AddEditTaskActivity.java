package com.julienarzul.basemvp.sample.ui.addEditTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.julienarzul.basemvp.MvpActivity;
import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.model.Task;

/**
 * Copyright @ Julien Arzul 2017
 */

public class AddEditTaskActivity extends MvpActivity implements AddEditTaskContract.View {

    private static final String EDITED_TASK_EXTRA_KEY = "com.julienarzul.basemvp.sample.ui.addEditTask.AddEditTaskActivity.editedTask";

    public static Intent getStartingIntent(Context context) {
        return getStartingIntent(context, null);
    }

    public static Intent getStartingIntent(Context context, Task editedTask) {
        Intent intent = new Intent(context, AddEditTaskActivity.class);

        if (editedTask != null) {
            intent.putExtra(EDITED_TASK_EXTRA_KEY, editedTask);
        }

        return intent;
    }

    @Override
    protected MvpContract.Presenter createPresenter() {
        return new AddEditTaskPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_task_add_edit);
    }
}
