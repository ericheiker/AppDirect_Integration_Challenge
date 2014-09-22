package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement(name = "result")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SubscriptionEventResult {

    @XmlElement
    private boolean success;

    @XmlElement
    private String message;

    @XmlElement
    private String errorCode;

    @XmlElement
    private String accountIdentifier;
}