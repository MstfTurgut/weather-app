package com.mstftrgt.place_api.domain.place.usecase;

import com.mstftrgt.place_api.domain.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceSave implements UseCase {

    private String cityTitle;
    private String districtTitle;
    private Long userId;
}
