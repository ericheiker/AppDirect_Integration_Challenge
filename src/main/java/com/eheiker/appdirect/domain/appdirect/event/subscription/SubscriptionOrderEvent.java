package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.eheiker.appdirect.domain.appdirect.event.Creator;
import com.eheiker.appdirect.domain.appdirect.event.Marketplace;
import com.eheiker.appdirect.domain.appdirect.event.Event;
import com.eheiker.appdirect.domain.appdirect.event.EventType;

@XmlRootElement(name = "event")
public class SubscriptionOrderEvent extends Event {

    private Marketplace marketplace;
    private Creator creator;
    private SubscriptionOrderPayload payload;

    public SubscriptionOrderEvent() {
        super(EventType.SUBSCRIPTION_ORDER);
    }

    public SubscriptionOrderPayload getPayload() {
        return payload;
    }

    public void setPayload(final SubscriptionOrderPayload payload) {
        this.payload = payload;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(final Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(final Creator creator) {
        this.creator = creator;
    }
}
