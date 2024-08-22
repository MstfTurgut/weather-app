package com.mstftrgt.place_api.infra.adapters.weatherinfo.rest;

import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.domain.weatherinfo.port.WeatherInfoPort;
import com.mstftrgt.place_api.infra.adapters.weatherinfo.rest.properties.OpenCageDataProperties;
import com.mstftrgt.place_api.infra.adapters.weatherinfo.rest.properties.OpenWeatherMapProperties;
import com.mstftrgt.place_api.infra.common.OpenCageDataUrlCallException;
import com.mstftrgt.place_api.infra.common.OpenWeatherMapUrlCallException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherInfoRestAdapter implements WeatherInfoPort {

    private final RestTemplate restTemplate;

    private final OpenCageDataProperties openCageDataProperties;

    private final OpenWeatherMapProperties openWeatherMapProperties;

    @Override
    public WeatherInfo retrieveWeatherInfo(String cityName, String districtName) {
        log.info("Retrieving weather info for city: {}, district: {}", cityName, districtName);

        LatLonData latLonData = callOpenCageDataApiToGetLatLonDataFromCityAndDistrictValues(cityName, districtName);

        WeatherData weatherData = callOpenWeatherMapApiToRetrieveWeatherData(latLonData);

        log.info("Retrieved weather info for city: {}, district: {}", cityName, districtName);
        return weatherData.toModel();
    }

    private LatLonData callOpenCageDataApiToGetLatLonDataFromCityAndDistrictValues(String cityName, String districtName) {
        String openCageDataUrl = openCageDataProperties.getBaseUrl() + "geocode/v1/json?q=" + cityName + "," + districtName + "&key=" + openCageDataProperties.getKey();

        log.debug("Calling OpenCageData API with URL: {}", openCageDataUrl);
        ResponseEntity<String> latLonResponse = callOpenCageDataApi(openCageDataUrl);
        log.debug("Received response from OpenCageData API: {}", latLonResponse.getBody());

        return LatLonData.from(latLonResponse.getBody());
    }

    private ResponseEntity<String> callOpenCageDataApi(String openCageDataUrl) {
        ResponseEntity<String> latLonResponse;
        try {
            latLonResponse = restTemplate.exchange(openCageDataUrl, HttpMethod.GET, new HttpEntity<>(null, null), String.class);
        } catch (Exception e) {
            throw new OpenCageDataUrlCallException();
        }
        return latLonResponse;
    }

    private WeatherData callOpenWeatherMapApiToRetrieveWeatherData(LatLonData latLonData) {
        String openWeatherMapUrl = openWeatherMapProperties.getBaseUrl() +
                "/data/2.5/weather?lat=" + latLonData.getLat() +
                "&lon=" + latLonData.getLon() +
                "&appid=" + openWeatherMapProperties.getKey();

        log.debug("Calling OpenWeatherMap API with URL: {}", openWeatherMapUrl);
        ResponseEntity<String> weatherResponse = callOpenWeatherMapApi(openWeatherMapUrl);
        log.debug("Received response from OpenWeatherMap API: {}", weatherResponse.getBody());

        return WeatherData.from(weatherResponse.getBody());
    }

    private ResponseEntity<String> callOpenWeatherMapApi(String openWeatherMapUrl) {
        ResponseEntity<String> weatherResponse;
        try {
            weatherResponse = restTemplate.exchange(openWeatherMapUrl, HttpMethod.GET, new HttpEntity<>(null, null), String.class);
        } catch (Exception e) {
            throw new OpenWeatherMapUrlCallException();
        }
        return weatherResponse;
    }
}
