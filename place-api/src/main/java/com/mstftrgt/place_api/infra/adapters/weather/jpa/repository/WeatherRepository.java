package com.mstftrgt.place_api.infra.adapters.weather.jpa.repository;


import com.mstftrgt.place_api.infra.adapters.weather.jpa.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    Optional<WeatherEntity> findByCityIdAndDistrictId(Long cityId, Long districtId);
}
