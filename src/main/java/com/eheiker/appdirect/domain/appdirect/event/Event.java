package com.eheiker.appdirect.domain.appdirect.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Event {
    private EventType type;
}