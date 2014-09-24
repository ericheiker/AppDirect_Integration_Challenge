package com.eheiker.appdirect.client.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.eheiker.appdirect.client.AppDirectClient;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetEventAction<T> extends AppDirectAction {

    private String token;
    private String url;

    public GetEventAction(final AppDirectClient client) {
        super(client);
    }

    @Override
    public <T> ActionResult<T> execute(Class<T> resultType) {
        T event = getEvent(url, token, resultType);

        return new ActionResult<T>(event);
    }

    protected <T> T getEvent(final String url, final String token, final Class<T> clazz) {
        return client.signAndGet(url, clazz);
    }
}
