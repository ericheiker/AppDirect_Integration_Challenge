package com.eheiker.appdirect.domain.myapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String openId;

    private String firstName;
    private String lastName;
}
