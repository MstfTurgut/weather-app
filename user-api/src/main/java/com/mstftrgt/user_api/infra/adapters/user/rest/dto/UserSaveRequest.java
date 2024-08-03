package com.mstftrgt.user_api.infra.adapters.user.rest.dto;

import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSaveRequest {

    private String firstName;
    private String lastName;
    private String city;
    private String district;

    public UserSave toUseCase() {
        return UserSave
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .city(city)
                .district(district)
                .build();
    }
}
