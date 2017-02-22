package com.julienarzul.basemvp.sample.ui.addEditTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;

import com.julienarzul.basemvp.MvpActivity;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.datasources.DatasourceFactory;
import com.julienarzul.basemvp.sample.core.model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright @ Julien Arzul 2017
 */

public class AddEditTaskActivity extends MvpActivity<AddEditTaskContract.Presenter> implements AddEditTaskContract.View {

    private static final String EDITED_TASK_EXTRA_KEY = "com.julienarzul.basemvp.sample.ui.addEditTask.AddEditTaskActivity.editedTask";
    @BindView(R.id.add_edit_task_id_textinputlayout)
    TextInputLayout taskIdTextInputLayout;
    @BindView(R.id.add_edit_task_description_textinputlayout)
    TextInputLayout taskDescriptionTextInputLayout;

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
    protected AddEditTaskContract.Presenter createPresenter() {
        Context context = this.getApplicationContext();
        return new AddEditTaskPresenter(DatasourceFactory.tasksDatasource(context));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_task_add_edit);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_edit_task_confirm_button)
    void onConfirmButtonClicked() {
        this.presenter.onConfirmButtonClicked(
                this.taskIdTextInputLayout.getEditText().getText().toString(),
                this.taskDescriptionTextInputLayout.getEditText().getText().toString());
    }

    @Override
    public void finishScreen() {
        this.finish();
    }
}
