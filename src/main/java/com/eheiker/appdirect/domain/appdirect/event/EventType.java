package com.eheiker.appdirect.domain.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
    SUBSCRIPTION_ORDER,
    SUBSCRIPTION_CANCEL,
    USER_ASSIGNED,
    USER_UNASSIGNED
}
