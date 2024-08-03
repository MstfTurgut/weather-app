package com.mstftrgt.place_api.infra.adapters.place.event;

import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSavedEvent {

    private Long id;
    private String city;
    private String district;

    public PlaceSave toPlaceSaveUseCase() {
        return PlaceSave.builder()
                .userId(id)
                .cityTitle(city)
                .districtTitle(district)
                .build();
    }
}
