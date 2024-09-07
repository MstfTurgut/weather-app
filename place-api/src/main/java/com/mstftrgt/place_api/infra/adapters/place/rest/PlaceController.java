package com.mstftrgt.place_api.infra.adapters.place.rest;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.model.PlaceDetailed;
import com.mstftrgt.place_api.domain.place.usecase.PlaceDetailedRetrieveAll;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.AllPlacesDetailedResponse;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.NewPlaceRequest;
import com.mstftrgt.place_api.infra.adapters.place.rest.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final UseCaseHandler<Place, PlaceSave> placeSaveUseCaseHandler;
    private final UseCaseHandler<List<PlaceDetailed>, PlaceDetailedRetrieveAll> placeDetailedRetrieveAllUseCaseHandler;

    @PostMapping("places/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResponse saveNewPlace(@RequestBody NewPlaceRequest newPlaceRequest, @PathVariable Long userId) {
        Place place = placeSaveUseCaseHandler.handle(newPlaceRequest.toUseCase(userId));
        return PlaceResponse.from(place);
    }


    @GetMapping("places/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AllPlacesDetailedResponse retrieveAllPlaces(@PathVariable Long userId) {
        List<PlaceDetailed> placeDetailedList = placeDetailedRetrieveAllUseCaseHandler.handle(new PlaceDetailedRetrieveAll(userId));
        return AllPlacesDetailedResponse.from(userId, placeDetailedList);
    }
}
