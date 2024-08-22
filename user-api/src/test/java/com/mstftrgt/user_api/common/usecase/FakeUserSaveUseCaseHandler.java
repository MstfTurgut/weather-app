package com.mstftrgt.user_api.common.usecase;

import com.mstftrgt.user_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeUserSaveUseCaseHandler implements UseCaseHandler<User, UserSave> {

    @Override
    public User handle(UserSave useCase) {
        return User.builder()
                .id(1L)
                .firstName(useCase.getFirstName())
                .lastName(useCase.getLastName())
                .build();
    }
}
