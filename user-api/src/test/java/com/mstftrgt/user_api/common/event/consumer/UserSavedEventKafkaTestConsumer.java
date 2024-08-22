package com.mstftrgt.user_api.common.event.consumer;

import com.mstftrgt.user_api.domain.user.event.UserSavedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserSavedEventKafkaTestConsumer extends AbstractEventKafkaTestConsumer<UserSavedEvent> {

    @KafkaListener(topics = "user_saved",
            groupId = "user-saved-consumer",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    @Override
    public void consume(UserSavedEvent event) {consumerInternal(event);}
}
