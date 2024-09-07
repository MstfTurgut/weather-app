package com.mstftrgt.place_api.infra.common;


import com.mstftrgt.place_api.infra.common.exception.CityNotFoundException;
import com.mstftrgt.place_api.infra.common.exception.DistrictNotFoundException;
import com.mstftrgt.place_api.infra.common.exception.OpenCageDataUrlCallException;
import com.mstftrgt.place_api.infra.common.exception.OpenWeatherMapUrlCallException;
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

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(OpenCageDataUrlCallException.class)
    protected ErrorResponse handleOpenCageDataUrlCallException(OpenCageDataUrlCallException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(OpenWeatherMapUrlCallException.class)
    protected ErrorResponse handleOpenWeatherUrlCallException(OpenWeatherMapUrlCallException exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
