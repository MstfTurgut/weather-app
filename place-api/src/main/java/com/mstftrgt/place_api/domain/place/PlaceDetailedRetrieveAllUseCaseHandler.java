package com.mstftrgt.place_api.domain.place;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.model.PlaceDetailed;
import com.mstftrgt.place_api.domain.place.port.PlacePort;
import com.mstftrgt.place_api.domain.place.usecase.PlaceDetailedRetrieveAll;
import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weather.port.WeatherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceDetailedRetrieveAllUseCaseHandler implements UseCaseHandler<List<PlaceDetailed>, PlaceDetailedRetrieveAll> {

    private final WeatherPort weatherPort;
    private final PlacePort placePort;

    @Override
    public List<PlaceDetailed> handle(PlaceDetailedRetrieveAll useCase) {
        List<Place> placeList = placePort.retrieveAll(useCase.getUserId());
        return mapToPlaceDetailedList(placeList);
    }

    private List<PlaceDetailed> mapToPlaceDetailedList(List<Place> places) {
        return places.stream().map((place -> {
            Weather weather = weatherPort.retrieveById(place.getWeatherId());
            return PlaceDetailed.from(place, weather);
        })).toList();
    }
}
