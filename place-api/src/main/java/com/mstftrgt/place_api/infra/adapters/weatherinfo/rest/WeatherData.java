package com.mstftrgt.place_api.infra.adapters.weatherinfo.rest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherData {

    private String main;
    private Double temp;
    private Double feelsLike;
    private Integer humidity;

    public static WeatherData from(String weatherDataResponseBody) {
        DocumentContext weatherContext = JsonPath.parse(weatherDataResponseBody);

        String main = weatherContext.read("$.weather[0].main");
        Double temp = weatherContext.read("$.main.temp");
        Double feelsLike = weatherContext.read("$.main.feels_like");
        Integer humidity = weatherContext.read("$.main.humidity");

        return WeatherData.builder()
                .main(main)
                .temp(temp)
                .feelsLike(feelsLike)
                .humidity(humidity)
                .build();
    }

    public WeatherInfo toModel() {
        return WeatherInfo.builder()
                .main(main)
                .temp(temp)
                .feelsLike(feelsLike)
                .humidity(humidity)
                .build();
    }
}
