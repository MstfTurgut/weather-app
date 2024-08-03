package com.mstftrgt.place_api.infra.adapters.weatherinfo.rest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LatLonData {

    private final Double lat;
    private final Double lon;

    public static LatLonData from(String latLonResponseBody) {
        DocumentContext latLonContext = JsonPath.parse(latLonResponseBody);
        Double lat = latLonContext.read("$.results[0].geometry.lat");
        Double lon = latLonContext.read("$.results[0].geometry.lng");
        return LatLonData.builder().lat(lat).lon(lon).build();
    }
}
