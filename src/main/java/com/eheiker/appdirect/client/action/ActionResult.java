package com.eheiker.appdirect.client.action;

public class ActionResult<T> {

    public ActionResult(T entity) {
        this.entity = entity;
    }

    private T entity;

    public T getEntity() {
        return entity;
    }
}
