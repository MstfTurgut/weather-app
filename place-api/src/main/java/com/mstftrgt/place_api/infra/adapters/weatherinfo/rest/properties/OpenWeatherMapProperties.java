package com.mstftrgt.place_api.infra.adapters.weatherinfo.rest.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.openweathermap")
public class OpenWeatherMapProperties {

    private String key;
    private String baseUrl;
}
