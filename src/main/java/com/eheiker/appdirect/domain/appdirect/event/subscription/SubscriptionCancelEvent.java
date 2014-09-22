package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.event.Event;
import com.eheiker.appdirect.domain.appdirect.event.EventType;

@Data
@ToString
@XmlRootElement(name = "event")
public class SubscriptionCancelEvent extends Event {

    private SubscriptionCancelPayload payload;

    public SubscriptionCancelEvent() {
        super(EventType.SUBSCRIPTION_CANCEL);
    }


}
