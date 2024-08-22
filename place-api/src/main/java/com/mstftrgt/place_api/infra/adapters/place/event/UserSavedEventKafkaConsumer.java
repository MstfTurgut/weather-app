package com.mstftrgt.place_api.infra.adapters.place.event;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSavedEventKafkaConsumer {

    private final UseCaseHandler<Place, PlaceSave> placeSaveUseCaseHandler;

    @KafkaListener(topics = "${kafka.topics.user-saved.topicName}",
            groupId = "${kafka.topics.user-saved.consumerGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consume(@Payload UserSavedEvent eventData) {
        log.info("User saved event received {}", eventData);
        try {
            placeSaveUseCaseHandler.handle(eventData.toModel());
        } catch (Exception e) {
            log.info("User saved event {} cannot be consumed", eventData.getId(), e);
        }
    }
}
