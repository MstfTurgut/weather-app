package com.mstftrgt.place_api.domain.place.port;

import com.mstftrgt.place_api.domain.place.model.Place;

public interface PlacePort {

    Place save(Long weatherId, Long userId);
}
