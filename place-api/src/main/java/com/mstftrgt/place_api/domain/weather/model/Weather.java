package com.mstftrgt.place_api.domain.weather.model;

import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Weather implements Serializable {

    private Long id;
    private Long cityId;
    private Long districtId;
    private WeatherInfo weatherInfo;
}
