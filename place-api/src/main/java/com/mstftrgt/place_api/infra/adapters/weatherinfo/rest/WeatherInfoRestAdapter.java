package com.mstftrgt.place_api.infra.adapters.weatherinfo.rest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.domain.weatherinfo.port.WeatherInfoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${api.opencagedata.key}")
    private String openCageDataApiKey;

    @Value("${api.openweathermap.key}")
    private String openWeatherMapApiKey;

    @Override
    public WeatherInfo retrieveWeatherInfo(String cityName, String districtName) {
        log.info("Retrieving weather info for city: {}, district: {}", cityName, districtName);

        LatLonData latLonData = callOpenCageDataApiToGetLatLonDataFromCityAndDistrictValues(cityName, districtName);

        WeatherData weatherData = callOpenWeatherMapApiToRetrieveWeatherData(latLonData);

        log.info("Retrieved weather info for city: {}, district: {}", cityName, districtName);
        return weatherData.toModel();
    }

    private LatLonData callOpenCageDataApiToGetLatLonDataFromCityAndDistrictValues(String cityName, String districtName) {
        String placeToLatLonDataRequestUrl = "https://api.opencagedata.com/geocode/v1/json?q="
                + cityName + "," + districtName + "&key=" + openCageDataApiKey;

        log.debug("Calling OpenCageData API with URL: {}", placeToLatLonDataRequestUrl);
        ResponseEntity<String> latLonResponse;
        try {
            latLonResponse = restTemplate.exchange(placeToLatLonDataRequestUrl, HttpMethod.GET, new HttpEntity<>(null, null), String.class);
        } catch (Exception e) {
            log.error("Error calling OpenCageData API: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve latitude and longitude data", e);
        }
        log.debug("Received response from OpenCageData API: {}", latLonResponse.getBody());

        return LatLonData.from(latLonResponse.getBody());
    }

    private WeatherData callOpenWeatherMapApiToRetrieveWeatherData(LatLonData latLonData) {
        String weatherInfoRequestUrl = "https://api.openweathermap.org/data/2.5/weather?lat="
                + latLonData.getLat() + "&lon=" + latLonData.getLon() + "&appid=" + openWeatherMapApiKey;

        log.debug("Calling OpenWeatherMap API with URL: {}", weatherInfoRequestUrl);
        ResponseEntity<String> weatherResponse;
        try {
            weatherResponse = restTemplate.exchange(weatherInfoRequestUrl, HttpMethod.GET, new HttpEntity<>(null, null), String.class);
        } catch (Exception e) {
            log.error("Error calling OpenWeatherMap API: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve weather data", e);
        }
        log.debug("Received response from OpenWeatherMap API: {}", weatherResponse.getBody());

        return WeatherData.from(weatherResponse.getBody());
    }
}
