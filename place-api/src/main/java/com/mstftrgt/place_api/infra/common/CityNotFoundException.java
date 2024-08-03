package com.mstftrgt.place_api.infra.common;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("City not found");
    }
}
