package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.infra.adapters.place.rest.dto.NewPlaceRequest;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.PlaceResponse;
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
public class PlaceControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void should_save_place() {
        NewPlaceRequest request = NewPlaceRequest.builder()
                .city("testCity")
                .district("testDistrict")
                .build();

        long userId = 1L;

        var responseEntity = testRestTemplate
                .exchange("/places/" + userId, HttpMethod.POST, new HttpEntity<>(request, null), PlaceResponse.class);

        assertThat(responseEntity).isNotNull()
                .returns(HttpStatus.CREATED, from(ResponseEntity::getStatusCode));

        assertThat(responseEntity.getBody()).isNotNull()
                .returns(1L, PlaceResponse::getId)
                .returns(1L, PlaceResponse::getWeatherId)
                .returns(1L, PlaceResponse::getUserId);
    }
}
