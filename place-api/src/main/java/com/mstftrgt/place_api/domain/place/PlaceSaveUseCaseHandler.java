package com.mstftrgt.place_api.domain.place;

import com.mstftrgt.place_api.domain.city.model.City;
import com.mstftrgt.place_api.domain.city.port.CityPort;
import com.mstftrgt.place_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.place_api.domain.district.model.District;
import com.mstftrgt.place_api.domain.district.port.DistrictPort;
import com.mstftrgt.place_api.domain.place.model.Place;
import com.mstftrgt.place_api.domain.place.port.PlacePort;
import com.mstftrgt.place_api.domain.place.usecase.PlaceSave;
import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weather.port.WeatherPort;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import com.mstftrgt.place_api.domain.weatherinfo.port.WeatherInfoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceSaveUseCaseHandler implements UseCaseHandler<Place, PlaceSave> {

    private final CityPort cityPort;
    private final PlacePort placePort;
    private final WeatherPort weatherPort;
    private final DistrictPort districtPort;
    private final WeatherInfoPort weatherInfoPort;


    @Override
    public Place handle(PlaceSave useCase) {
        log.info("Handling PlaceSave use case for user ID: {}", useCase.getUserId());
        City city = cityPort.retrieve(useCase.getCityTitle());
        District district = districtPort.retrieve(city.getId(), useCase.getDistrictTitle());

        Weather weather = retrieveWeatherIfExistsOrElseSaveAndReturn(useCase, city, district);

        return placePort.save(weather.getId(), useCase.getUserId());
    }

    private Weather retrieveWeatherIfExistsOrElseSaveAndReturn(PlaceSave useCase, City city, District district) {
        Weather weather = weatherPort.retrieve(city.getId(), district.getId());

        if (weather == null) {
            log.info("No existing weather record found for {}/{}, saving new weather record.", district.getTitle(), city.getTitle());
            return saveNewWeatherAndReturn(useCase, city.getId(), district.getId());
        } else {
            log.info("Weather record found for {}/{}, using existing weather record.", district.getTitle(), city.getTitle());
            return weather;
        }
    }

    private Weather saveNewWeatherAndReturn(PlaceSave useCase, Long cityId, Long districtId) {
        WeatherInfo weatherInfo = weatherInfoPort.retrieveWeatherInfo(useCase.getCityTitle(), useCase.getDistrictTitle());
        return weatherPort.save(weatherInfo, cityId, districtId);
    }
}
