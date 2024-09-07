package com.mstftrgt.place_api.domain.place.usecase;

import com.mstftrgt.place_api.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlaceDetailedRetrieveAll implements UseCase {

    Long userId;
}
