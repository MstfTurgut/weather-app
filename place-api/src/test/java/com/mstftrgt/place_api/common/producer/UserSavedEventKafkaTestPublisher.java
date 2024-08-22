package com.mstftrgt.place_api.common.producer;

import com.mstftrgt.place_api.infra.adapters.place.event.UserSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSavedEventKafkaTestPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(UserSavedEvent userSavedEvent) {
        log.info("Test published user saved event {}", userSavedEvent);
        Map<String, Object> headers = Map.of(
                KafkaHeaders.TOPIC, "user_saved",
                KafkaHeaders.KEY, userSavedEvent.getId().toString()
        );

        kafkaTemplate.send(new GenericMessage<>(userSavedEvent, headers));
    }
}