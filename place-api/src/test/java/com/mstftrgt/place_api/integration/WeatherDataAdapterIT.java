package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.WeatherDataAdapter;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.entity.WeatherEntity;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/weather_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class WeatherDataAdapterIT {

    @Autowired
    WeatherDataAdapter weatherDataAdapter;

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void should_save_weather() {
        WeatherInfo weatherInfo = WeatherInfo.builder()
                .main("testMain")
                .temp(1.0)
                .humidity(1)
                .feelsLike(1.0)
                .build();

        Weather savedWeather = weatherDataAdapter.save(weatherInfo, 34L, 433L);

        Optional<WeatherEntity> weatherEntity = weatherRepository.findById(savedWeather.getId());
        assertThat(weatherEntity.isPresent()).isTrue();
        assertThat(weatherEntity.get().toModel()).isEqualTo(savedWeather);
    }

    @Test
    void should_retrieve_weather() {
        Weather weather = weatherDataAdapter.retrieve(1L, 1L);
        assertThat(weather).isNotNull()
                .returns(1L, Weather::getId)
                .returns("testMain", w -> w.getWeatherInfo().getMain())
                .returns(1, w -> w.getWeatherInfo().getHumidity())
                .returns(1.0, w -> w.getWeatherInfo().getTemp())
                .returns(1.0, w -> w.getWeatherInfo().getFeelsLike());
    }

    @Test
    void should_retrieve_all_weather() {
        List<Weather> weathers = weatherDataAdapter.retrieveAll();
        assertThat(weathers).hasSize(2).containsExactlyInAnyOrder(
                Weather.builder()
                        .id(1L)
                        .cityId(1L)
                        .districtId(1L)
                        .weatherInfo(new WeatherInfo("testMain", 1.0, 1.0, 1))
                        .build(),
                Weather.builder()
                        .id(2L)
                        .cityId(2L)
                        .districtId(2L)
                        .weatherInfo(new WeatherInfo("testMain", 1.0, 1.0, 1))
                        .build()
        );
    }

}
