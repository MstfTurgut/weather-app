package com.mstftrgt.user_api.integration;

import com.mstftrgt.user_api.common.event.consumer.UserSavedEventKafkaTestConsumer;
import com.mstftrgt.user_api.domain.user.event.UserSavedEvent;
import com.mstftrgt.user_api.infra.adapters.user.event.UserSavedEventAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSavedEventAdapterIT {

    @Autowired
    UserSavedEventKafkaTestConsumer userSavedEventKafkaTestConsumer;

    @Autowired
    UserSavedEventAdapter userSavedEventAdapter;

    @Test
    void should_send_notification() {
        UserSavedEvent userSavedEvent = UserSavedEvent.builder()
                .id(1L)
                .city("İstanbul")
                .district("Esenyurt")
                .build();

        userSavedEventAdapter.publish(userSavedEvent);

        userSavedEventKafkaTestConsumer.wait(5, 1);
        List<UserSavedEvent> userSavedEvents = userSavedEventKafkaTestConsumer.popAll();

        assertThat(userSavedEvents).hasSize(1);
        assertThat(userSavedEvents.get(1))
                .returns(1L, from(UserSavedEvent::getId));
    }

}
