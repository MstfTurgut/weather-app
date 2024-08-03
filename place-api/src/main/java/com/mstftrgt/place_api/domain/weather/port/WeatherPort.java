package com.mstftrgt.place_api.domain.weather.port;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;

import java.util.List;

public interface WeatherPort {

    Weather save(WeatherInfo weatherInfo, Long cityId, Long districtId);

    Weather retrieve(Long cityId, Long districtId);

    List<Weather> retrieveAll();

    void update(Weather weather);
}
