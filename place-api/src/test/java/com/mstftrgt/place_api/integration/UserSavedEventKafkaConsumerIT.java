package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.common.EventAssertion;
import com.mstftrgt.place_api.common.producer.UserSavedEventKafkaTestPublisher;
import com.mstftrgt.place_api.common.usecase.FakePlaceSaveUseCaseHandler;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import com.mstftrgt.place_api.infra.adapters.place.event.UserSavedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(properties = {"consumer-integration-test.enabled=true"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSavedEventKafkaConsumerIT {

    @Autowired
    FakePlaceSaveUseCaseHandler placeSaveUseCaseHandler;

    @Autowired
    UserSavedEventKafkaTestPublisher userSavedEventKafkaTestPublisher;
    
    EventAssertion<UserSavedEvent, PlaceSave> eventAssertion = new EventAssertion<>();


    @Test
    void should_receive_user_saved_events() {
        UserSavedEvent userSavedEvent = UserSavedEvent.builder()
                .id(1L)
                .city("testCity")
                .district("testDistrict")
                .build();


        userSavedEventKafkaTestPublisher.publish(userSavedEvent);

        eventAssertion.assertEventProcessed(
                5,
                userSavedEvent,
                () -> placeSaveUseCaseHandler.getProcessedPlaceSave());
    }
}
