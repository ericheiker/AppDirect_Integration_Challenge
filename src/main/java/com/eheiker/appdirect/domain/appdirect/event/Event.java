package com.eheiker.appdirect.domain.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@XmlRootElement(name = "event")
public class Event {
    private EventType type;
}
