package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement(name = "result")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SubscriptionEventResult {

    private boolean success;
    private String message;
    private String errorCode;
    private String accountIdentifier;
}