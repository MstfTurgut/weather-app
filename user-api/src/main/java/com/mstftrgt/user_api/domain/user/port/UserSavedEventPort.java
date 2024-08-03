package com.mstftrgt.user_api.domain.user.port;

import com.mstftrgt.user_api.domain.common.event.EventPublisher;
import com.mstftrgt.user_api.domain.user.event.UserSavedEvent;

public interface UserSavedEventPort extends EventPublisher<UserSavedEvent> {

    void publish(UserSavedEvent userSavedEvent);
}
