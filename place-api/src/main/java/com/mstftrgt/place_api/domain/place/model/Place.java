package com.mstftrgt.place_api.domain.place.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Place {

    private Long id;
    private Long weatherId;
    private Long userId;
}
