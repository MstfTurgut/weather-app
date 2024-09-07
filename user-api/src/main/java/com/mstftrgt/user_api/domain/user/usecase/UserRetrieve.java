package com.mstftrgt.user_api.domain.user.usecase;

import com.mstftrgt.user_api.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRetrieve implements UseCase {

    private String firstName;
    private String lastName;
}
