package com.mstftrgt.place_api.infra.adapters.district.jpa;

import com.mstftrgt.place_api.domain.district.model.District;
import com.mstftrgt.place_api.domain.district.port.DistrictPort;
import com.mstftrgt.place_api.infra.adapters.district.jpa.entity.DistrictEntity;
import com.mstftrgt.place_api.infra.adapters.district.jpa.repository.DistrictRepository;
import com.mstftrgt.place_api.infra.common.DistrictNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DistrictDataAdapter implements DistrictPort {

    private final DistrictRepository districtRepository;

    @Override
    public District retrieve(Long cityId, String districtTitle) {
        DistrictEntity districtEntity = districtRepository
                .findByTitleAndCityId(districtTitle, cityId).orElseThrow(DistrictNotFoundException::new);
        return districtEntity.toModel();
    }

    @Override
    public District retrieveById(Long id) {
        DistrictEntity districtEntity = districtRepository
                .findById(id).orElseThrow(DistrictNotFoundException::new);
        return districtEntity.toModel();
    }
}
