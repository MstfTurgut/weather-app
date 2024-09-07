package com.mstftrgt.user_api.integration;

import com.mstftrgt.user_api.infra.adapters.user.rest.dto.UserResponse;
import com.mstftrgt.user_api.infra.adapters.user.rest.dto.UserSaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void should_save_user() {
        UserSaveRequest userSaveRequest = UserSaveRequest.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .build();

        var responseEntity = testRestTemplate
                .exchange("/users", HttpMethod.POST, new HttpEntity<>(userSaveRequest, null), UserResponse.class);

        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.CREATED, from(ResponseEntity::getStatusCode));

        assertThat(responseEntity.getBody()).isNotNull()
                .returns(1L, UserResponse::getId)
                .returns("testFirstName", UserResponse::getFirstName)
                .returns("testLastName", UserResponse::getLastName);
    }

    @Test
    void should_retrieve_user() {
        var responseEntity = testRestTemplate
                .exchange("/users?firstName=testFirstName&lastName=testLastName", HttpMethod.GET, null, UserResponse.class);

        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.OK, from(ResponseEntity::getStatusCode));

        assertThat(responseEntity.getBody()).isNotNull()
                .returns(1L, UserResponse::getId)
                .returns("testFirstName", UserResponse::getFirstName)
                .returns("testLastName", UserResponse::getLastName);
    }
}
