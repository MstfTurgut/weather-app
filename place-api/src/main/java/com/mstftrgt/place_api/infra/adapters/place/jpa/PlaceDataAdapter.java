package com.mstftrgt.place_api.infra.adapters.place.jpa;

import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.port.PlacePort;
import com.mstftrgt.place_api.infra.adapters.place.jpa.entity.PlaceEntity;
import com.mstftrgt.place_api.infra.adapters.place.jpa.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceDataAdapter implements PlacePort {

    private final PlaceRepository placeRepository;

    @Override
    public Place save(Long weatherId, Long userId) {
        PlaceEntity placeEntity = PlaceEntity.builder()
                .weatherId(weatherId)
                .userId(userId)
                .build();

        return placeRepository.save(placeEntity).toModel();
    }
}
