package com.mstftrgt.place_api.infra.adapters.place.rest.dto;

import com.mstftrgt.place_api.domain.place.model.PlaceDetailed;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllPlacesDetailedResponse {

    private Long userId;
    private List<PlaceDetailedResponse> placeDetailedResponseList;
 
    public static AllPlacesDetailedResponse from(Long userId, List<PlaceDetailed> placeDetailedList) {
        return AllPlacesDetailedResponse.builder()
                .userId(userId)
                .placeDetailedResponseList(placeDetailedList.stream().map(PlaceDetailedResponse::from).toList())
                .build();
    }
}