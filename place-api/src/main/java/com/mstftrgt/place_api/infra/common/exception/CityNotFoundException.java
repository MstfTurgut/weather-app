package com.mstftrgt.place_api.infra.common.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("City not found");
    }
}
