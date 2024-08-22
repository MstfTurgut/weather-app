package com.mstftrgt.place_api.external;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.mstftrgt.place_api.PlaceApiApplication;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ActiveProfiles("contract")
@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureStubRunner(ids = "com.mstftrgt:user-api", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, PlaceApiApplication.class})
public class UserApiExternalContractIT {

    @Container
    static KafkaContainer kafka = new KafkaContainer();

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }


    @Autowired
    StubTrigger trigger;


    @Test
    void shouldProcessUserSaved(CapturedOutput output) {
        trigger.trigger("user_saved");

        Awaitility.await().atMost(Duration.ofSeconds(3))
                .pollInterval(Duration.ofMillis(500))
                .untilAsserted(() -> assertThat(output).contains("Fake handling place save use case"));
    }

}


@Configuration
class TestConfig {


    @Bean
    MessageVerifierSender<Message<?>> standaloneMessageVerifier(KafkaTemplate kafkaTemplate) {
        return new MessageVerifierSender<>() {

            @Override
            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
            }

            @Override
            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
                Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
                newHeaders.put(KafkaHeaders.TOPIC, destination);
                kafkaTemplate.send(MessageBuilder.createMessage(payload, new MessageHeaders(newHeaders)));
            }
        };
    }

    @Bean
    @Primary
    JsonMessageConverter noopMessageConverter() {
        return new NoopJsonMessageConverter();
    }
}

class NoopJsonMessageConverter extends JsonMessageConverter {

    NoopJsonMessageConverter() {
    }

    @Override
    protected Object convertPayload(Message<?> message) {
        return message.getPayload();
    }
}