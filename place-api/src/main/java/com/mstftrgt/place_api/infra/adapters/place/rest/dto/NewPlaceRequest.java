package com.mstftrgt.place_api.infra.adapters.place.rest.dto;

import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPlaceRequest {
    private String city;
    private String district;

    public PlaceSave toUseCase(Long userId) {
        return PlaceSave.builder()
                .cityTitle(city)
                .districtTitle(district)
                .userId(userId)
                .build();
    }
}
