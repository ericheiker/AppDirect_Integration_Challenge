package com.eheiker.appdirect.domain.appdirect.event;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.Creator;
import com.eheiker.appdirect.domain.appdirect.Marketplace;

@Data
@ToString
public abstract class Event {
    private EventType type;
    private Marketplace marketplace;
    private Creator creator;

    public Event(EventType type) {
        this.type = type;
    }
}
