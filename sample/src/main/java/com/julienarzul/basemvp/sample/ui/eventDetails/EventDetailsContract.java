package com.julienarzul.basemvp.sample.ui.eventDetails;

import com.julienarzul.basemvp.MvpContract;
import com.julienarzul.basemvp.sample.core.model.Event;

/**
 * Copyright @ Julien Arzul 2017
 */

interface EventDetailsContract {

    interface View extends MvpContract.View {

        void setContentViewVisible(boolean visible);

        void setProgressBarVisible(boolean visible);

        void setEventNameText(String eventNameText);

        void setEventDateText(String eventDateText);

        void displayUpdateEventClickedSnackbar(Event event);
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void loadEvent(int eventId);

        void onUpdateButtonClicked();
    }
}
