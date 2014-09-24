package com.eheiker.appdirect.client.action;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.domain.appdirect.event.access.UserUnassignedEvent;

public class GetUserUnassignedEventAction extends GetEventAction {

    public GetUserUnassignedEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<UserUnassignedEvent> execute() {
        return super.execute(UserUnassignedEvent.class);
    }
}
