package com.mstftrgt.place_api.domain.district.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class District {

    private Long id;
    private String title;
    private Long cityId;
}
