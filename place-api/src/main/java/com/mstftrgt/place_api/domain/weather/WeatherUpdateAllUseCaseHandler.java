package com.mstftrgt.place_api.domain.weather;

import com.mstftrgt.place_api.domain.city.model.City;
import com.mstftrgt.place_api.domain.city.port.CityPort;
import com.mstftrgt.place_api.domain.common.usecase.VoidEmptyUseCaseHandler;
import com.mstftrgt.place_api.domain.district.model.District;
import com.mstftrgt.place_api.domain.district.port.DistrictPort;
import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weather.port.WeatherPort;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.domain.weatherinfo.port.WeatherInfoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherUpdateAllUseCaseHandler implements VoidEmptyUseCaseHandler {

    private final CityPort cityPort;
    private final WeatherPort weatherPort;
    private final DistrictPort districtPort;
    private final WeatherInfoPort weatherInfoPort;


    @Override
    public void handle() {
        log.info("Starting weather data update process.");
        List<Weather> weatherData = weatherPort.retrieveAll();
        log.info("Retrieved {} weather records.", weatherData.size());
        weatherData.forEach(this::updateWeatherWithCurrentWeatherInfo);
        log.info("Weather data update process completed.");
    }

    private void updateWeatherWithCurrentWeatherInfo(Weather weather) {
        WeatherInfo retrievedWeatherInfo = retrieveCurrentWeatherInfo(weather);
        weather.setWeatherInfo(retrievedWeatherInfo);
        weatherPort.update(weather);
    }

    private WeatherInfo retrieveCurrentWeatherInfo(Weather weather) {
        City city = cityPort.retrieveById(weather.getCityId());
        District district = districtPort.retrieveById(weather.getDistrictId());
        return weatherInfoPort.retrieveWeatherInfo(city.getTitle(), district.getTitle());
    }
}
