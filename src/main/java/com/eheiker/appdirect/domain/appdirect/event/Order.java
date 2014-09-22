package com.eheiker.appdirect.domain.appdirect.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement
    private String editionCode;

    @XmlElement(name = "item")
    private OrderItem orderItem;

    public String getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(final String editionCode) {
        this.editionCode = editionCode;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(final OrderItem orderItem) {
        this.orderItem = orderItem;
    }

}
