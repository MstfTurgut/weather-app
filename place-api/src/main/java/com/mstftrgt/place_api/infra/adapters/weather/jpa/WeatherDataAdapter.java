package com.mstftrgt.place_api.infra.adapters.weather.jpa;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weather.port.WeatherPort;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.entity.WeatherEntity;
import com.mstftrgt.place_api.infra.adapters.weather.jpa.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherDataAdapter implements WeatherPort {

    private final WeatherRepository weatherRepository;

    @Override
    public Weather save(WeatherInfo weatherInfo, Long cityId, Long districtId) {
        WeatherEntity weatherEntity = WeatherEntity.builder()
                .cityId(cityId)
                .districtId(districtId)
                .main(weatherInfo.getMain())
                .temp(weatherInfo.getTemp())
                .humidity(weatherInfo.getHumidity())
                .feelsLike(weatherInfo.getFeelsLike())
                .build();

        WeatherEntity savedWeatherEntity = weatherRepository.save(weatherEntity);

        return savedWeatherEntity.toModel();
    }

    @Override
    public Optional<Weather> retrieveByCityAndDistrict(Long cityId, Long districtId) {
        Optional<WeatherEntity> weatherEntity = weatherRepository.findByCityIdAndDistrictId(cityId, districtId);
        return weatherEntity.map(WeatherEntity::toModel);
    }

    @Override
    @Cacheable("weatherCache")
    public Weather retrieveById(Long weatherId) {
        return weatherRepository.findById(weatherId).orElseThrow().toModel();
    }

    @Override
    public List<Weather> retrieveAll() {
        return weatherRepository.findAll().stream().map(WeatherEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public void update(Weather weather) {
        weatherRepository.save(WeatherEntity.from(weather));
    }
}
