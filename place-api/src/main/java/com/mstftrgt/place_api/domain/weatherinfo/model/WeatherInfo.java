package com.mstftrgt.place_api.domain.weatherinfo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WeatherInfo {

    private String main;
    private Double temp;
    private Double feelsLike;
    private Integer humidity;
}
