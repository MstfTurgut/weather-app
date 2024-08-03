package com.mstftrgt.place_api.infra.adapters.weather.scheduled;

import com.mstftrgt.place_api.domain.common.usecase.VoidEmptyUseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherDataUpdateScheduled {

    @Qualifier("weatherUpdateAllUseCaseHandler")
    private final VoidEmptyUseCaseHandler weatherUpdateAllUseCaseHandler;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000) // 5 min
    public void updateAllWeatherData() {
        log.info("updating weather data: {}", dateFormat.format(new Date()));
        weatherUpdateAllUseCaseHandler.handle();
    }
}
