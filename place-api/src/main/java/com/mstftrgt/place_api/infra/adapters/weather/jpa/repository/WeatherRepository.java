package com.mstftrgt.place_api.infra.adapters.weather.jpa.repository;


import com.mstftrgt.place_api.infra.adapters.weather.jpa.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    WeatherEntity findByCityIdAndDistrictId(Long cityId, Long districtId);
}
