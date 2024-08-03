package com.mstftrgt.place_api.domain.weatherinfo.port;

import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;

public interface WeatherInfoPort {

    WeatherInfo retrieveWeatherInfo(String cityName, String districtName);
}
