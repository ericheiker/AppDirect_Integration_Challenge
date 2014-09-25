package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.Account;

@Data
@ToString
@XmlRootElement(name = "payload")
public class SubscriptionCancelPayload {
    Account account;
}
