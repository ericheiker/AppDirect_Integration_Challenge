package com.eheiker.appdirect.domain.appdirect.event;

public class Event {

    private EventType type;

    public Event(final EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(final EventType type) {
        this.type = type;
    }
}
