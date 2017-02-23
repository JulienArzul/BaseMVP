package com.julienarzul.basemvp.sample.ui.eventDetails;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.core.datasources.IEventDatasource;

/**
 * Copyright @ Julien Arzul 2017
 */

class EventDetailsPresenter extends BasePresenter<EventDetailsContract.View> implements EventDetailsContract.Presenter {

    private final IEventDatasource eventDatasource;

    EventDetailsPresenter(IEventDatasource eventDatasource) {
        this.eventDatasource = eventDatasource;
    }
}
