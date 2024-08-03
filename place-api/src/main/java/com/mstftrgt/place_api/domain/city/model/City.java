package com.mstftrgt.place_api.domain.city.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {

    private Long id;
    private String title;
}
