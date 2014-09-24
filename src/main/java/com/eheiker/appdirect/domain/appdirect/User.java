package com.eheiker.appdirect.domain.appdirect;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@XmlRootElement
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String language;
    private String openId;
    private String uuid;
}
