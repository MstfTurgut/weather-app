package com.mstftrgt.place_api.common.usecase;

import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.place.model.PlaceDetailed;
import com.mstftrgt.place_api.domain.place.usecase.PlaceDetailedRetrieveAll;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class FakePlaceDetailedRetrieveAllUseCaseHandler implements UseCaseHandler<List<PlaceDetailed>, PlaceDetailedRetrieveAll> {
    @Override
    public List<PlaceDetailed> handle(PlaceDetailedRetrieveAll useCase) {
        WeatherInfo weatherInfo = WeatherInfo.builder()
                .main("main")
                .temp(1.0)
                .feelsLike(1.0)
                .humidity(1)
                .build();

        return List.of(PlaceDetailed.builder()
                        .id(1L)
                        .userId(useCase.getUserId())
                        .weatherId(1L)
                        .cityId(1L)
                        .districtId(1L)
                        .weatherInfo(weatherInfo)
                        .build());
    }
}
