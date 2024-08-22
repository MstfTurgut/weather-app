package com.mstftrgt.place_api.infra.common;

public class OpenCageDataUrlCallException extends RuntimeException {

    public OpenCageDataUrlCallException() {
        super("Failed to retrieve latitude and longitude data.");
    }
}
