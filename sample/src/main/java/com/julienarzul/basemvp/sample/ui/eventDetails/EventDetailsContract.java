package com.julienarzul.basemvp.sample.ui.eventDetails;

import com.julienarzul.basemvp.MvpContract;

/**
 * Copyright @ Julien Arzul 2017
 */

interface EventDetailsContract {

    interface View extends MvpContract.View {

        void setEventNameText(String eventNameText);

        void setEventDateText(String eventDateText);
    }

    interface Presenter extends MvpContract.Presenter<View> {

        void loadEvent(int eventId);
    }
}