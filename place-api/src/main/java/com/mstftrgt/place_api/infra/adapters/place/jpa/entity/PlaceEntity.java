package com.mstftrgt.place_api.infra.adapters.place.jpa.entity;

import com.mstftrgt.place_api.domain.place.model.Place;
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
@Table(name = "place", uniqueConstraints = { @UniqueConstraint(columnNames = { "weatherId", "userId"})})
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "weather_id")
    private Long weatherId;

    @Column(name = "user_id")
    private Long userId;

    public Place toModel() {
        return Place.builder()
                .id(id)
                .weatherId(weatherId)
                .userId(userId)
                .build();
    }
}
