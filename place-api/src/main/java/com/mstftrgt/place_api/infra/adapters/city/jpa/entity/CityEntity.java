package com.mstftrgt.place_api.infra.adapters.city.jpa.entity;

import com.mstftrgt.place_api.domain.city.model.City;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class CityEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    public City toModel() {
        return City.builder()
                .id(id)
                .title(title)
                .build();
    }
}
