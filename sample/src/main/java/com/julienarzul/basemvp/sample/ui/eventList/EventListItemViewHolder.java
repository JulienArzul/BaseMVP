package com.julienarzul.basemvp.sample.ui.eventList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.model.Event;

/**
 * Copyright @ Julien Arzul 2017
 */
class EventListItemViewHolder extends RecyclerView.ViewHolder {

    private final EventListContract.Presenter presenter;

    private final TextView eventNameTextView;
    private final TextView eventDateTextView;

    private EventListItemViewHolder(View itemView, EventListContract.Presenter presenter) {
        super(itemView);

        this.presenter = presenter;

        this.eventNameTextView = (TextView) itemView.findViewById(R.id.event_name_textview);
        this.eventDateTextView = (TextView) itemView.findViewById(R.id.event_date_textview);
    }

    static EventListItemViewHolder newInstance(Context context, ViewGroup parent, EventListContract.Presenter presenter) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);

        return new EventListItemViewHolder(itemView, presenter);
    }

    void bindView(Event item) {
        Context context = this.itemView.getContext();

        this.eventNameTextView.setText(item.eventName());
        this.eventDateTextView.setText(DateFormat.getDateFormat(context).format(item.eventDate()));
    }
}
