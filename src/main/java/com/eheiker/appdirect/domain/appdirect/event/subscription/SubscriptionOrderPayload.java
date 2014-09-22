package com.eheiker.appdirect.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.eheiker.appdirect.domain.appdirect.event.Company;
import com.eheiker.appdirect.domain.appdirect.event.Order;

@XmlRootElement(name = "payload")
public class SubscriptionOrderPayload {
    private Company company;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Company getCompany() {

        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }
}
