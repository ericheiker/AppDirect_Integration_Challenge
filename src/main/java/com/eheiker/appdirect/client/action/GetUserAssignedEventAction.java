package com.eheiker.appdirect.client.action;

import com.eheiker.appdirect.client.AppDirectClient;
import com.eheiker.appdirect.domain.appdirect.event.access.UserAssignedEvent;

public class GetUserAssignedEventAction extends GetEventAction {

    public GetUserAssignedEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<UserAssignedEvent> execute() {
        return super.execute(UserAssignedEvent.class);
    }
}
