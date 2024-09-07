package com.mstftrgt.place_api.infra.adapters.place.jpa.repository;

import com.mstftrgt.place_api.infra.adapters.place.jpa.entity.PlaceEntity;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

    List<PlaceEntity> findAllByUserId(Long userId);
}
