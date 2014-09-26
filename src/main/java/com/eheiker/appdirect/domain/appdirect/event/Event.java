package com.eheiker.appdirect.domain.appdirect.event;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.Marketplace;
import com.eheiker.appdirect.domain.appdirect.User;

@Data
@ToString
public abstract class Event {
    private EventType type;
    private Marketplace marketplace;
    private User creator;

    public Event(EventType type) {
        this.type = type;
    }
}
