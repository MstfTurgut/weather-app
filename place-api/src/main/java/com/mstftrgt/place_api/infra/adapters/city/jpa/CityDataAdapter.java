package com.mstftrgt.place_api.infra.adapters.city.jpa;

import com.mstftrgt.place_api.domain.city.model.City;
import com.mstftrgt.place_api.domain.city.port.CityPort;
import com.mstftrgt.place_api.infra.adapters.city.jpa.entity.CityEntity;
import com.mstftrgt.place_api.infra.adapters.city.jpa.repository.CityRepository;
import com.mstftrgt.place_api.infra.common.CityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityDataAdapter implements CityPort {

    private final CityRepository cityRepository;

    @Override
    public City retrieve(String title) {
        CityEntity cityEntity = cityRepository.findByTitle(title).orElseThrow(CityNotFoundException::new);
        return cityEntity.toModel();
    }

    @Override
    public City retrieveById(Long id) {
        CityEntity cityEntity = cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
        return cityEntity.toModel();
    }
}
