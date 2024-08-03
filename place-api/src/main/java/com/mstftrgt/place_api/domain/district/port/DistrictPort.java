package com.mstftrgt.place_api.domain.district.port;

import com.mstftrgt.place_api.domain.district.model.District;

public interface DistrictPort {

    District retrieve(Long cityId, String districtTitle);

    District retrieveById(Long id);
}
