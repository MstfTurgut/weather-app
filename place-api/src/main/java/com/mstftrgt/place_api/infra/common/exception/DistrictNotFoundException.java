package com.mstftrgt.place_api.infra.common.exception;

public class DistrictNotFoundException extends RuntimeException {

    public DistrictNotFoundException() {
        super("District not found");
    }
}
