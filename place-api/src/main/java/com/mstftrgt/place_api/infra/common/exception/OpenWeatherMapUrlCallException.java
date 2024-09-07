package com.mstftrgt.place_api.infra.common.exception;

public class OpenWeatherMapUrlCallException extends RuntimeException {

    public OpenWeatherMapUrlCallException() {
        super("Failed to retrieve weather data.");
    }
}
