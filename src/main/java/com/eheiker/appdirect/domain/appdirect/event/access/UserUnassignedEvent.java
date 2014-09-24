package com.eheiker.appdirect.domain.appdirect.event.access;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.event.Event;
import com.eheiker.appdirect.domain.appdirect.event.EventType;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "event")
public class UserUnassignedEvent extends Event {

    private AssignmentPayload payload;

    public UserUnassignedEvent() {
        super(EventType.USER_UNASSIGNED);
    }
}
