package com.mstftrgt.user_api.domain.user;

import com.mstftrgt.user_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.port.UserPort;
import com.mstftrgt.user_api.domain.user.usecase.UserRetrieve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRetrieveUseCaseHandler implements UseCaseHandler<User, UserRetrieve> {

    private final UserPort userPort;

    @Override
    public User handle(UserRetrieve useCase) {
        return userPort.retrieveByFirstNameAndLastName(useCase.getFirstName(), useCase.getLastName());
    }
}
