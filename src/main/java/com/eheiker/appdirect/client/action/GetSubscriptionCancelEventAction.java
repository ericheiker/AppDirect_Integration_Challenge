package com.eheiker.appdirect.client.action;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionCancelEvent;

public class GetSubscriptionCancelEventAction extends GetEventAction<SubscriptionCancelEvent> {

    public GetSubscriptionCancelEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<SubscriptionCancelEvent> execute() {
        return execute(SubscriptionCancelEvent.class);
    }
}
