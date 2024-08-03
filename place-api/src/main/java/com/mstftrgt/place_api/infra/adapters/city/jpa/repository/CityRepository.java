package com.mstftrgt.place_api.infra.adapters.city.jpa.repository;

import com.mstftrgt.place_api.infra.adapters.city.jpa.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByTitle(String title);
}
