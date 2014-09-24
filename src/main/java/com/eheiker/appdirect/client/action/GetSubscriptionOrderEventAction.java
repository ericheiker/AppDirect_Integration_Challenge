package com.eheiker.appdirect.client.action;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.domain.appdirect.event.subscription.SubscriptionOrderEvent;

public class GetSubscriptionOrderEventAction extends GetEventAction {

    public GetSubscriptionOrderEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<SubscriptionOrderEvent> execute() {
        return execute(SubscriptionOrderEvent.class);
    }
}
