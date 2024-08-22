package com.mstftrgt.place_api.infra.common;

public class OpenWeatherMapUrlCallException extends RuntimeException {

    public OpenWeatherMapUrlCallException() {
        super("Failed to retrieve weather data.");
    }
}
