package com.julienarzul.basemvp.sample.ui.eventDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julienarzul.basemvp.MvpFragment;
import com.julienarzul.basemvp.sample.DatasourceFactory;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.model.Event;

/**
 * Copyright @ Julien Arzul 2017
 */

public class EventDetailsFragment extends MvpFragment<EventDetailsContract.Presenter> implements EventDetailsContract.View {

    private static final String EVENT_ID_BUNDLE_KEY = "com.julienarzul.basemvp.sample.ui.eventDetails.EventDetailsFragment.eventId";

    private View containerView;
    private TextView eventNameTextView;
    private TextView eventDateTextView;
    private View progressBar;

    public static EventDetailsFragment newInstance(int eventId) {
        Bundle args = new Bundle();
        args.putInt(EVENT_ID_BUNDLE_KEY, eventId);

        EventDetailsFragment fragment = new EventDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected EventDetailsContract.Presenter createPresenter() {
        return new EventDetailsPresenter(DatasourceFactory.eventDatasource());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = this.getArguments();
        if (!arguments.containsKey(EVENT_ID_BUNDLE_KEY)) {
            throw new IllegalArgumentException("Impossible to use EventDetailsFragment without an event id");
        }

        this.presenter.loadEvent(arguments.getInt(EVENT_ID_BUNDLE_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        containerView = view.findViewById(R.id.event_details_content_container);
        progressBar = view.findViewById(R.id.event_details_progressbar);

        eventNameTextView = (TextView) view.findViewById(R.id.event_details_name_textview);
        eventDateTextView = (TextView) view.findViewById(R.id.event_details_date_textview);

        view.findViewById(R.id.event_details_update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void setContentViewVisible(boolean visible) {
        this.containerView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        this.progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setEventNameText(String eventNameText) {
        this.eventNameTextView.setText(eventNameText);
    }

    @Override
    public void setEventDateText(String eventDateText) {
        this.eventDateTextView.setText(eventDateText);
    }

    @Override
    public void displayUpdateEventClickedSnackbar(Event event) {
        Snackbar.make(this.containerView, String.format("Button Update %s", event.eventName()), Snackbar.LENGTH_SHORT)
                .show();
    }

    private void onUpdateButtonClicked() {
        this.presenter.onUpdateButtonClicked();
    }
}
