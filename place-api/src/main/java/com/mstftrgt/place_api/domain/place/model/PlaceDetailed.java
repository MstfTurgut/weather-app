package com.mstftrgt.place_api.domain.place.model;

import com.mstftrgt.place_api.domain.place.usecase.PlaceDetailedRetrieveAll;
import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDetailed {

    private Long id;
    private Long userId;
    private Long weatherId;
    private Long cityId;
    private Long districtId;
    private WeatherInfo weatherInfo;
    
    public static PlaceDetailed from(Place place, Weather weather) {
        return PlaceDetailed.builder()
                .id(place.getId())
                .userId(place.getUserId())
                .weatherId(weather.getId())
                .cityId(weather.getCityId())
                .districtId(weather.getDistrictId())
                .weatherInfo(weather.getWeatherInfo())
                .build();
    }
    
}
