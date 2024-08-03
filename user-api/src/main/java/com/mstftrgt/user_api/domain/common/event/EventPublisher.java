package com.mstftrgt.user_api.domain.common.event;


import com.mstftrgt.user_api.domain.common.model.Event;

public interface EventPublisher<T extends Event> {

    void publish(T event);
}
