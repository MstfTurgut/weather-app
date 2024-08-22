package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.city.model.City;
import com.mstftrgt.place_api.infra.adapters.city.jpa.CityDataAdapter;
import com.mstftrgt.place_api.infra.common.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityDataAdapterIT {

    @Autowired
    CityDataAdapter cityDataAdapter;

    void should_retrieve_city_by_title() {
        City city = cityDataAdapter.retrieve("İstanbul");
        assertThat(city).isNotNull()
                .returns("İstanbul", City::getTitle)
                .returns(34, City::getId);
    }

    void should_fail_retrieve_city_by_title() {
        assertThatExceptionOfType(CityNotFoundException.class)
                .isThrownBy(() -> cityDataAdapter.retrieve("UnknownCity"))
                .withMessage("City not found");
    }

    void should_retrieve_city_by_id() {
        City city = cityDataAdapter.retrieveById(34L);
        assertThat(city).isNotNull()
                .returns("İstanbul", City::getTitle)
                .returns(34, City::getId);
    }

    void should_fail_retrieve_city_by_id() {
        assertThatExceptionOfType(CityNotFoundException.class)
                .isThrownBy(() -> cityDataAdapter.retrieveById(999L))
                .withMessage("City not found");
    }
}
