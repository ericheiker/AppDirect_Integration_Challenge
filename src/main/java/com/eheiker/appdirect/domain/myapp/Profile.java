package com.eheiker.appdirect.domain.myapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
}
