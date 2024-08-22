package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.infra.adapters.place.jpa.PlaceDataAdapter;
import com.mstftrgt.place_api.infra.adapters.place.jpa.entity.PlaceEntity;
import com.mstftrgt.place_api.infra.adapters.place.jpa.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PlaceDataAdapterIT {

    @Autowired
    PlaceDataAdapter placeDataAdapter;

    @Autowired
    PlaceRepository placeRepository;

    void should_save_place() {
        Place savedPlace = placeDataAdapter.save(1L, 1L);

        Optional<PlaceEntity> placeEntity = placeRepository.findById(savedPlace.getId());
        assertThat(placeEntity.isPresent()).isTrue();
        assertThat(placeEntity.get().toModel()).isEqualTo(savedPlace);
    }

}
