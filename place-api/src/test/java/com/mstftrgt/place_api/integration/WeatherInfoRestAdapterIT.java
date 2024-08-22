package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.infra.adapters.weatherinfo.rest.WeatherInfoRestAdapter;
import com.mstftrgt.place_api.infra.common.OpenCageDataUrlCallException;
import com.mstftrgt.place_api.infra.common.OpenWeatherMapUrlCallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
@AutoConfigureWireMock(port = 9780)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherInfoRestAdapterIT {

    @Autowired
    private WeatherInfoRestAdapter weatherInfoRestAdapter;
    
    @Test
    void should_call_opencagedata_api_and_openweathermap_api() {
        stubFor(
                get(urlMatching("/geocode/v1/json\\?q=Istanbul,Esenyurt&key=1f0dc6ea9e104595a09ebd7a3043db7d"))
                        .willReturn(aResponse()
                                .withBodyFile("latlon.json")
                                .withHeader("Content-Type", "application/json")
                                .withStatus(HttpStatus.OK.value())
                        )
        );

        stubFor(
                get(urlMatching("/data/2.5/weather\\?lat=41.0342862&lon=28.6801113&appid=e332f889872927d3404af882963090cb"))
                        .willReturn(aResponse()
                                .withBodyFile("weather.json")
                                .withHeader("Content-Type", "application/json")
                                .withStatus(HttpStatus.OK.value())
                        )
        );

        WeatherInfo expectedWeatherInfo = WeatherInfo.builder()
                .main("Clear")
                .temp(301.53)
                .feelsLike(302.14)
                .humidity(51)
                .build();

        WeatherInfo weatherInfo = weatherInfoRestAdapter.retrieveWeatherInfo("Istanbul", "Esenyurt");

        assertThat(weatherInfo).usingRecursiveComparison().isEqualTo(expectedWeatherInfo);
    }

    @Test
    void should_fail_calling_opencagedata_api() {
        stubFor(
                get(urlMatching("/geocode/v1/json\\?q=Istanbul,failOpenCageData&key=1f0dc6ea9e104595a09ebd7a3043db7d"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.BAD_REQUEST.value())
                        )
        );

        assertThatExceptionOfType(OpenCageDataUrlCallException.class)
                .isThrownBy(() -> weatherInfoRestAdapter.retrieveWeatherInfo("Istanbul", "failOpenCageData"))
                .withMessage("Failed to retrieve latitude and longitude data.");
    }

    @Test
    void should_call_opencagedata_api_and_fail_openweathermap_api() {
        stubFor(get(urlMatching("/geocode/v1/json\\?q=Istanbul,failOpenWeatherMap&key=1f0dc6ea9e104595a09ebd7a3043db7d"))
                .willReturn(aResponse()
                        .withBodyFile("latlon_for_weather_fail.json")
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.OK.value())
                )
        );

        stubFor(
                get(urlMatching("/data/2.5/weather\\?lat=40.8851987&lon=29.2726983&appid=e332f889872927d3404af882963090cb"))
                        .willReturn(aResponse()
                                .withStatus(HttpStatus.BAD_REQUEST.value())
                        )
        );

        assertThatExceptionOfType(OpenWeatherMapUrlCallException.class)
                .isThrownBy(() -> weatherInfoRestAdapter.retrieveWeatherInfo("Istanbul", "failOpenWeatherMap"))
                .withMessage("Failed to retrieve weather data.");
    }
}