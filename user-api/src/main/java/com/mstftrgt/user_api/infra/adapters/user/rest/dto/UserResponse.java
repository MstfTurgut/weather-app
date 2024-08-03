package com.mstftrgt.user_api.infra.adapters.user.rest.dto;

import com.mstftrgt.user_api.domain.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;

    public static UserResponse from(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
