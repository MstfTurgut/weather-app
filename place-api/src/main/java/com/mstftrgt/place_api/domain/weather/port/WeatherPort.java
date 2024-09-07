package com.mstftrgt.place_api.domain.weather.port;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;

import java.util.List;
import java.util.Optional;

public interface WeatherPort {

    Weather save(WeatherInfo weatherInfo, Long cityId, Long districtId);

    Optional<Weather> retrieveByCityAndDistrict(Long cityId, Long districtId);

    Weather retrieveById(Long weatherId);

    List<Weather> retrieveAll();

    void update(Weather weather);
}
