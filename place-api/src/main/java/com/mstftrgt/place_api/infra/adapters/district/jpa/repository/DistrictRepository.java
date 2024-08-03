package com.mstftrgt.place_api.infra.adapters.district.jpa.repository;


import com.mstftrgt.place_api.infra.adapters.district.jpa.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

    Optional<DistrictEntity> findByTitleAndCityId(String title, Long cityId);
}
