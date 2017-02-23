package com.julienarzul.basemvp.sample.ui.eventList;

import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.core.model.Event;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */

interface EventListContract {

    interface View extends MvpContract.View {

        void setEventList(List<Event> eventList);

        void displayEventClickedSnackbar(Event event);

        void setProgressBarVisible(boolean visible);

        void setRecyclerViewVisible(boolean visible);
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void loadData();

        void onEventClicked(Event event);
    }
}
