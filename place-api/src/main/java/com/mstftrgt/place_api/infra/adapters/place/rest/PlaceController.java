package com.mstftrgt.place_api.infra.adapters.place.rest;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.NewPlaceRequest;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final UseCaseHandler<Place, PlaceSave> placeSaveUseCaseHandler;

    @PostMapping("places/{userId}")
    public PlaceResponse saveNewPlace(@RequestBody NewPlaceRequest newPlaceRequest, @PathVariable Long userId) {
        Place place = placeSaveUseCaseHandler.handle(newPlaceRequest.toUseCase(userId));
        return PlaceResponse.from(place);
    }
}
