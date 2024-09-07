package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.infra.adapters.place.jpa.PlaceDataAdapter;
import com.mstftrgt.place_api.infra.adapters.place.jpa.entity.PlaceEntity;
import com.mstftrgt.place_api.infra.adapters.place.jpa.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/place_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PlaceDataAdapterIT {

    @Autowired
    PlaceDataAdapter placeDataAdapter;

    @Autowired
    PlaceRepository placeRepository;

    @Test
    void should_save_place() {
        Place savedPlace = placeDataAdapter.save(1L, 1L);

        Optional<PlaceEntity> placeEntity = placeRepository.findById(savedPlace.getId());
        assertThat(placeEntity.isPresent()).isTrue();
        assertThat(placeEntity.get().toModel()).isEqualTo(savedPlace);
    }

    @Test
    void should_retrieve_all_places() {
        List<Place> places = placeDataAdapter.retrieveAll(2L);

        assertThat(places).isNotNull();
        assertThat(places.size()).isEqualTo(1);
        assertThat(places.get(0))
                .returns(2L, Place::getWeatherId)
                .returns(2L, Place::getUserId);
    }

}
