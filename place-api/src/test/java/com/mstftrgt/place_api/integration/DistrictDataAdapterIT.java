package com.mstftrgt.place_api.integration;

import com.mstftrgt.place_api.domain.district.model.District;
import com.mstftrgt.place_api.infra.adapters.district.jpa.DistrictDataAdapter;
import com.mstftrgt.place_api.infra.common.DistrictNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistrictDataAdapterIT {

    @Autowired
    DistrictDataAdapter districtDataAdapter;

    void should_retrieve_district_by_cityId_and_title() {
        District district = districtDataAdapter.retrieve(34L, "Esenyurt");
        assertThat(district).isNotNull()
                .returns("Esenyurt", District::getTitle)
                .returns(433L, District::getId)
                .returns(34L, District::getCityId);
    }

    void should_fail_retrieve_district_by_cityId_and_title() {
        assertThatExceptionOfType(DistrictNotFoundException.class)
                .isThrownBy(() -> districtDataAdapter.retrieve(99L, "Esenyurt"))
                .withMessage("District not found");
    }

    void should_retrieve_district_by_id() {
        District district = districtDataAdapter.retrieveById(433L);
        assertThat(district).isNotNull()
                .returns("Esenyurt", District::getTitle)
                .returns(433L, District::getId)
                .returns(34L, District::getCityId);
    }

    void should_fail_retrieve_district_by_id() {
        assertThatExceptionOfType(DistrictNotFoundException.class)
                .isThrownBy(() -> districtDataAdapter.retrieveById(9999L))
                .withMessage("District not found");
    }
}
