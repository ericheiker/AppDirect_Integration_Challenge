package com.eheiker.appdirect.domain.appdirect.event;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement
public class Marketplace {
    private String partner;
    private String baseUrl;
}
