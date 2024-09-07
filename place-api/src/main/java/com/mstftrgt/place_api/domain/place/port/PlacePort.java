package com.mstftrgt.place_api.domain.place.port;

import com.mstftrgt.place_api.domain.place.model.Place;

import java.util.List;

public interface PlacePort {

    Place save(Long weatherId, Long userId);
    
    List<Place> retrieveAll(Long userId);
}
