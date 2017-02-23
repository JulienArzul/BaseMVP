package com.julienarzul.basemvp.sample.ui.eventDetails;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.core.datasources.DataCallback;
import com.julienarzul.basemvp.sample.core.datasources.IEventDatasource;
import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Event;

import java.lang.ref.WeakReference;
import java.text.DateFormat;

/**
 * Copyright @ Julien Arzul 2017
 */

class EventDetailsPresenter extends BasePresenter<EventDetailsContract.View> implements EventDetailsContract.Presenter {

    private final IEventDatasource eventDatasource;

    private Event event;

    EventDetailsPresenter(IEventDatasource eventDatasource) {
        this.eventDatasource = eventDatasource;
    }

    @Override
    public void loadEvent(int eventId) {
        this.view.setProgressBarVisible(true);
        this.view.setContentViewVisible(false);

        this.eventDatasource.getEventDetails(eventId, new GetEventDetailsDataCallback(this));
    }

    @Override
    public void onUpdateButtonClicked() {
        if (this.event != null) {
            this.view.displayUpdateEventClickedSnackbar(this.event);
        }
    }

    private void onGetEventDetailsSucceeded(Event event) {
        this.event = event;

        if (this.view != null) {
            this.view.setProgressBarVisible(false);
            this.view.setContentViewVisible(true);

            this.view.setEventNameText(event.eventName());
            this.view.setEventDateText(DateFormat.getDateInstance().format(event.eventDate()));
        }
    }

    private void onGetEventDetailsFailed(DatasourceError error) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);

            // TODO
        }
    }

    private static class GetEventDetailsDataCallback implements DataCallback<Event> {

        private final WeakReference<EventDetailsPresenter> presenterWeak;

        private GetEventDetailsDataCallback(EventDetailsPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(Event data) {
            EventDetailsPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetEventDetailsSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            EventDetailsPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetEventDetailsFailed(error);
            }
        }
    }
}
