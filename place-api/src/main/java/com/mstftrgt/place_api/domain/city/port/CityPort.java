package com.mstftrgt.place_api.domain.city.port;

import com.mstftrgt.place_api.domain.city.model.City;

public interface CityPort {

    City retrieve(String title);

    City retrieveById(Long id);
}
