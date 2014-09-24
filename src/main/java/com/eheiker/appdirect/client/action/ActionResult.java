package com.eheiker.appdirect.client.action;

import lombok.Data;

@Data
public class ActionResult<T> {

    private T entity;
    private boolean success;
    private String errorMessage;

    public ActionResult() {}

    public ActionResult(T entity) {
        this.entity = entity;
    }
}
