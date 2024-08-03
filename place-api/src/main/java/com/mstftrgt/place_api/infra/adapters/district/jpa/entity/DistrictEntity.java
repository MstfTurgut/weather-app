package com.mstftrgt.place_api.infra.adapters.district.jpa.entity;

import com.mstftrgt.place_api.domain.district.model.District;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "district")
public class DistrictEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "city_id")
    private Long cityId;

    public District toModel() {
        return District.builder()
                .id(id)
                .title(title)
                .cityId(cityId)
                .build();
    }
}
