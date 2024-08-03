package com.mstftrgt.place_api.domain.weather.model;

import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {

    private Long id;
    private Long cityId;
    private Long districtId;
    private WeatherInfo weatherInfo;
}
