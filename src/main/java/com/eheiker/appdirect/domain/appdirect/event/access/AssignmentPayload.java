package com.eheiker.appdirect.domain.appdirect.event.access;

import lombok.Data;
import lombok.ToString;

import com.eheiker.appdirect.domain.appdirect.Account;
import com.eheiker.appdirect.domain.appdirect.User;

@Data
@ToString
public class AssignmentPayload {
    Account account;
    User user;
}
