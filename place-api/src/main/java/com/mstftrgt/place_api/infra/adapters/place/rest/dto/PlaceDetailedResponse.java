package com.mstftrgt.place_api.infra.adapters.place.rest.dto;

import com.mstftrgt.place_api.domain.place.model.PlaceDetailed;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDetailedResponse {
    private Long id;
    private Long weatherId;
    private Long cityId;
    private Long districtId;
    private WeatherInfo weatherInfo;
    
    public static PlaceDetailedResponse from(PlaceDetailed placeDetailed) {
        return PlaceDetailedResponse.builder()
                .id(placeDetailed.getId())
                .weatherId(placeDetailed.getWeatherId())
                .cityId(placeDetailed.getCityId())
                .districtId(placeDetailed.getDistrictId())
                .weatherInfo(placeDetailed.getWeatherInfo())
                .build();
    }
}
