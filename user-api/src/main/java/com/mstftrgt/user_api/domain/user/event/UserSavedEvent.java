package com.mstftrgt.user_api.domain.user.event;

import com.mstftrgt.user_api.domain.common.model.Event;
import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSavedEvent implements Event {

    private Long id;
    private String city;
    private String district;

    public static UserSavedEvent from(UserSave userSave, Long id) {
        return UserSavedEvent.builder()
                .id(id)
                .city(userSave.getCity())
                .district(userSave.getDistrict())
                .build();
    }
}
