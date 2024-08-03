package com.mstftrgt.place_api.infra.adapters.place.jpa.repository;

import com.mstftrgt.place_api.infra.adapters.place.jpa.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
