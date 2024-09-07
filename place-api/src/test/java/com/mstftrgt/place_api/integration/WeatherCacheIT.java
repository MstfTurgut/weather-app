package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.WeatherDataAdapter;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.entity.WeatherEntity;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherCacheIT {

    @MockBean
    private  WeatherRepository mockWeatherRepository;

    @Autowired
    private WeatherDataAdapter weatherDataAdapter;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenRetrieveWeatherById_thenWeatherReturnedFromCache() {
        WeatherEntity weatherEntity = new WeatherEntity(1L, 1L, 1L, "main", 1.0, 1.0, 1);
        given(mockWeatherRepository.findById(1L))
                .willReturn(Optional.of(weatherEntity));

        Weather weatherCacheMiss = weatherDataAdapter.retrieveById(1L);
        Weather weatherCacheHit = weatherDataAdapter.retrieveById(1L);

        assertThat(weatherCacheMiss).isEqualTo(weatherEntity.toModel());
        assertThat(weatherCacheHit).isEqualTo(weatherEntity.toModel());

        verify(mockWeatherRepository, times(1)).findById(1L);
        assertThat(weatherFromCache()).isEqualTo(weatherEntity.toModel());
    }

    private Object weatherFromCache() {
        return cacheManager.getCache("weatherCache").get(1L).get();
    }
}