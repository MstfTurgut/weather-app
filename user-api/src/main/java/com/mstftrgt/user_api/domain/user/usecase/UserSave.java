package com.mstftrgt.user_api.domain.user.usecase;

import com.mstftrgt.user_api.domain.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSave implements UseCase {

    private String firstName;
    private String lastName;
    private String city;
    private String district;
}
