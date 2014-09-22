package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.event.Company;
import com.eheiker.appdirect.domain.appdirect.event.Order;

@Data
@ToString
@XmlRootElement(name = "payload")
public class SubscriptionOrderPayload {
    private Company company;
    private Order order;
}
