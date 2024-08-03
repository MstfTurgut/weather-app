package com.mstftrgt.place_api.infra.common;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    protected ErrorResponse handleCityNotFoundException(CityNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DistrictNotFoundException.class)
    protected ErrorResponse handleDistrictNotFoundException(DistrictNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
