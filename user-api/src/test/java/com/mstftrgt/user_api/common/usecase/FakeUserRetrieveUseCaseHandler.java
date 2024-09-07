package com.mstftrgt.user_api.common.usecase;

import com.mstftrgt.user_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.usecase.UserRetrieve;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class FakeUserRetrieveUseCaseHandler implements UseCaseHandler<User, UserRetrieve> {
    @Override
    public User handle(UserRetrieve useCase) {
        return User.builder()
                .id(1L)
                .firstName(useCase.getFirstName())
                .lastName(useCase.getLastName())
                .build();
    }
}
