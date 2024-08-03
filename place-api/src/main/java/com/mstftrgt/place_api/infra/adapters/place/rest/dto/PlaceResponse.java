package com.mstftrgt.place_api.infra.adapters.place.rest.dto;

import com.mstftrgt.place_api.domain.place.model.Place;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceResponse {

    private Long id;
    private Long weatherId;
    private Long userId;

    public static PlaceResponse from(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .userId(place.getUserId())
                .weatherId(place.getWeatherId())
                .build();
    }
}
