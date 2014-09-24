package com.eheiker.appdirect.client.action;

import com.eheiker.appdirect.client.AppDirectClient;

public abstract class AppDirectAction<T> implements IAppDirectAction {

    protected AppDirectClient client;

    public AppDirectAction(AppDirectClient client) {
        this.client = client;
    }
}
