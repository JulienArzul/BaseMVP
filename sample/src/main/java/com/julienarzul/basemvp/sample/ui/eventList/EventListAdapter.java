package com.julienarzul.basemvp.sample.ui.eventList;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.julienarzul.basemvp.sample.core.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
class EventListAdapter extends RecyclerView.Adapter<EventListItemViewHolder> {

    private final EventListContract.Presenter presenter;

    private final List<Event> itemList;

    EventListAdapter(EventListContract.Presenter presenter) {
        this.presenter = presenter;

        this.itemList = new ArrayList<>();
    }

    @Override
    public EventListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return EventListItemViewHolder.newInstance(parent.getContext(), parent, this.presenter);
    }

    @Override
    public void onBindViewHolder(EventListItemViewHolder holder, int position) {
        holder.bindView(this.itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    void setItemList(List<Event> itemList) {
        this.itemList.clear();

        if (itemList != null) {
            this.itemList.addAll(itemList);
        }

        this.notifyDataSetChanged();
    }
}
