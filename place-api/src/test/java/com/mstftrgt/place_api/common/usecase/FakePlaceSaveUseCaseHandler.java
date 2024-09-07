package com.mstftrgt.place_api.common.usecase;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@Primary
public class FakePlaceSaveUseCaseHandler implements UseCaseHandler<Place, PlaceSave> {

    private final static String UNKNOWN_TITLE = "unknown";

    private PlaceSave processedPlaceSave;

    @Override
    public Place handle(PlaceSave useCase) {
        log.info("Fake handling place save use case");
        processedPlaceSave = useCase;
        return Place.builder()
                .id(1L)
                .weatherId(1L)
                .userId(1L)
                .build();
    }
}
