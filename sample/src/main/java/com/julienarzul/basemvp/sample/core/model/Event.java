package com.julienarzul.basemvp.sample.core.model;

import java.util.Date;

/**
 * Copyright @ Julien Arzul 2017
 */
public class Event {

    private final String eventName;
    private final Date eventDate;

    private Event(String eventName, Date eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public static Event create(String eventName, Date eventDate) {
        return new Event(eventName, eventDate);
    }

    public String eventName() {
        return eventName;
    }

    public Date eventDate() {
        return eventDate;
    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null) return false;
        return eventDate != null ? eventDate.equals(event.eventDate) : event.eventDate == null;

    }
}
