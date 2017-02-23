package com.julienarzul.basemvp.sample.ui.eventList;

import com.julienarzul.basemvp.BasePresenter;
import com.julienarzul.basemvp.sample.core.datasources.DataCallback;
import com.julienarzul.basemvp.sample.core.datasources.IEventDatasource;
import com.julienarzul.basemvp.sample.core.model.DatasourceError;
import com.julienarzul.basemvp.sample.core.model.Event;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

class EventListPresenter extends BasePresenter<EventListContract.View> implements EventListContract.Presenter {

    private final IEventDatasource eventDatasource;

    EventListPresenter(IEventDatasource eventDatasource) {
        this.eventDatasource = eventDatasource;
    }

    @Override
    public void loadData() {
        this.view.setProgressBarVisible(true);
        this.view.setRecyclerViewVisible(false);

        this.eventDatasource.getEventList(new GetEventListDataCallback(this));
    }

    @Override
    public void onEventClicked(Event event) {
        this.view.displayEventClickedSnackbar(event);
    }

    private void onGetEventListSucceeded(List<Event> eventList) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);
            this.view.setRecyclerViewVisible(true);

            this.view.setEventList(eventList);
        }
    }

    private void onGetEventListFailed(DatasourceError error) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);

            // TODO: Show error view
        }
    }

    private static class GetEventListDataCallback implements DataCallback<List<Event>> {

        private final WeakReference<EventListPresenter> presenterWeak;

        private GetEventListDataCallback(EventListPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Event> data) {
            EventListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetEventListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            EventListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetEventListFailed(error);
            }
        }
    }
}
