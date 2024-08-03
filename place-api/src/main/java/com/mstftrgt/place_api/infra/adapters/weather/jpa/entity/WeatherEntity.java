package com.mstftrgt.place_api.infra.adapters.weather.jpa.entity;

import com.mstftrgt.place_api.domain.weather.model.Weather;
import com.mstftrgt.place_api.domain.weatherinfo.model.WeatherInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather", uniqueConstraints = { @UniqueConstraint(columnNames = { "cityId", "districtId" })})
public class WeatherEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "main")
    private String main;

    @Column(name = "temp")
    private Double temp;

    @Column(name = "feels_like")
    private Double feelsLike;

    @Column(name = "humidity")
    private Integer humidity;

    public Weather toModel() {
        WeatherInfo weatherInfo = WeatherInfo.builder()
                .main(main)
                .temp(temp)
                .feelsLike(feelsLike)
                .humidity(humidity)
                .build();

        return Weather.builder()
                .id(id)
                .cityId(cityId)
                .districtId(districtId)
                .weatherInfo(weatherInfo)
                .build();
    }

    public static WeatherEntity from(Weather weather) {
        return WeatherEntity.builder()
                .id(weather.getId())
                .cityId(weather.getCityId())
                .districtId(weather.getDistrictId())
                .main(weather.getWeatherInfo().getMain())
                .temp(weather.getWeatherInfo().getTemp())
                .feelsLike(weather.getWeatherInfo().getFeelsLike())
                .humidity(weather.getWeatherInfo().getHumidity())
                .build();
    }


}
