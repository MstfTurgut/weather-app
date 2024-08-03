package com.mstftrgt.user_api.infra.adapters.user.event;

import com.mstftrgt.user_api.domain.user.event.UserSavedEvent;
import com.mstftrgt.user_api.domain.user.port.UserSavedEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSavedEventAdapter implements UserSavedEventPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.user-saved.topicName}")
    private String TOPIC_NAME;

    @Override
    public void publish(UserSavedEvent userSavedEvent) {
        Map<String, Object> headers = Map.of(
                KafkaHeaders.TOPIC, TOPIC_NAME,
                KafkaHeaders.KEY, userSavedEvent.getId().toString()
        );

        log.info("Sending userSavedEvent : {}", userSavedEvent);
        kafkaTemplate.send(new GenericMessage<>(userSavedEvent, headers));
    }
}
