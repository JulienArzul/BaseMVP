package com.julienarzul.basemvp.sample.ui.eventList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.julienarzul.basemvp.MvpActivity;
import com.julienarzul.basemvp.sample.DatasourceFactory;
import com.julienarzul.basemvp.sample.R;
import com.julienarzul.basemvp.sample.core.model.Event;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

public class EventListActivity extends MvpActivity<EventListContract.Presenter> implements EventListContract.View {

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, EventListActivity.class);
    }

    @Override
    protected EventListContract.Presenter createPresenter() {
        return new EventListPresenter(DatasourceFactory.eventDatasource());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_list);

        this.presenter.loadData();
    }

    @Override
    public void setEventList(List<Event> eventList) {
        // TODO
    }
}
