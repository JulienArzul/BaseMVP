package com.julienarzul.basemvp.sample.ui.taskDetails;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.julienarzul.basemvp.MvpFragment;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.datasources.DatasourceFactory;
import com.julienarzul.basemvp.sample.core.model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright @ Julien Arzul 2017
 */

public class TaskDetailsFragment extends MvpFragment<TaskDetailsContract.Presenter> implements TaskDetailsContract.View {

    private static final String TASK_BUNDLE_KEY = "com.julienarzul.basemvp.sample.ui.taskDetails.TaskDetailsFragment.task";
    @BindView(R.id.task_details_task_description_textview)
    TextView taskDescriptionTextView;
    private Unbinder unbinder;

    public static TaskDetailsFragment newInstance(Task task) {
        Bundle args = new Bundle();
        args.putParcelable(TASK_BUNDLE_KEY, task);

        TaskDetailsFragment fragment = new TaskDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TaskDetailsContract.Presenter createPresenter() {
        return new TaskDetailsPresenter(DatasourceFactory.tasksDatasource(this.getContext()));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.unbinder != null) {
            this.unbinder.unbind();
            this.unbinder = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);

        this.unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setTaskDescriptionText(String taskDescription) {
        this.taskDescriptionTextView.setText(taskDescription);
    }

    @Override
    public void displayDeleteConfirmationToast() {
        Toast.makeText(this.getContext(), R.string.task_details_delete_confirmation_toast_text, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void displayErrorDialog() {
        DeleteErrorDialogFragment.newInstance().show(this.getFragmentManager(), DeleteErrorDialogFragment.TAG);
    }

    @Override
    public void finishScreen() {
        this.getActivity().finish();
    }

    @OnClick(R.id.task_details_delete_task_button)
    void onDeleteButtonClicked() {
        this.presenter.onDeleteButtonClicked();
    }

    public static class DeleteErrorDialogFragment extends DialogFragment {

        public static final String TAG = "DeleteErrorDialogFragment";

        public static DeleteErrorDialogFragment newInstance() {
            Bundle args = new Bundle();

            DeleteErrorDialogFragment fragment = new DeleteErrorDialogFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

            builder.setTitle(R.string.task_details_delete_error_dialog_title_text);
            builder.setMessage(R.string.task_details_delete_error_dialog_message_text);
            builder.setPositiveButton(android.R.string.ok, null);

            return builder.create();
        }
    }
}
