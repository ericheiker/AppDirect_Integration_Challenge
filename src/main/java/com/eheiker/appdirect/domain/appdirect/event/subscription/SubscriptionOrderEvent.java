package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.event.Creator;
import com.eheiker.appdirect.domain.appdirect.event.Marketplace;
import com.eheiker.appdirect.domain.appdirect.event.Event;
import com.eheiker.appdirect.domain.appdirect.event.EventType;

@Data
@ToString(callSuper = true)
@XmlRootElement(name = "event")
public class SubscriptionOrderEvent extends Event {

    private Marketplace marketplace;
    private Creator creator;
    private SubscriptionOrderPayload payload;

    public SubscriptionOrderEvent() {
        super(EventType.SUBSCRIPTION_ORDER);
    }
}
