package com.julienarzul.basemvp.sample.ui.eventDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julienarzul.basemvp.MvpFragment;
import com.julienarzul.basemvp.sample.DatasourceFactory;
import com.julienarzul.basemvp.sample.R;

/**
 * Copyright @ Julien Arzul 2017
 */

public class EventDetailsFragment extends MvpFragment<EventDetailsContract.Presenter> implements EventDetailsContract.View {

    private static final String EVENT_ID_BUNDLE_KEY = "com.julienarzul.basemvp.sample.ui.eventDetails.EventDetailsFragment.eventId";

    private TextView eventNameTextView;
    private TextView eventDateTextView;

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

        eventNameTextView = (TextView) view.findViewById(R.id.event_details_name_textview);
        eventDateTextView = (TextView) view.findViewById(R.id.event_details_date_textview);

        return view;
    }

    @Override
    public void setEventNameText(String eventNameText) {
        this.eventNameTextView.setText(eventNameText);
    }

    @Override
    public void setEventDateText(String eventDateText) {
        this.eventDateTextView.setText(eventDateText);
    }
}
