package com.mstftrgt.place_api.infra.common.exception;

public class OpenCageDataUrlCallException extends RuntimeException {

    public OpenCageDataUrlCallException() {
        super("Failed to retrieve latitude and Longitude data.");
    }
}
