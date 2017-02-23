package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
public class EventDatasource implements IEventDatasource {

    private static List<Event> createEventList() {
        List<Event> eventList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 20; i++) {
            String eventName = "Event " + (i + 1);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            eventList.add(Event.create(eventName, calendar.getTime()));
        }

        return eventList;
    }

    @Override
    public void getEventList(DataCallback<List<Event>> callback) {
        new WaitAsyncTask<>(callback, createEventList()).execute(3);
    }
}
