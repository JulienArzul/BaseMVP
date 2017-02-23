package com.julienarzul.basemvp.sample.core.datasources;

import com.julienarzul.basemvp.sample.core.model.Event;

import java.util.List;

/**
 * Copyright @ Julien Arzul 2017
 */
public interface IEventDatasource {

    void getEventList(DataCallback<List<Event>> callback);

    void getEventDetails(int eventId, DataCallback<Event> callback);
}
