package com.mstftrgt.user_api.domain.user;

import com.mstftrgt.user_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.user_api.domain.user.event.UserSavedEvent;
import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.port.UserPort;
import com.mstftrgt.user_api.domain.user.port.UserSavedEventPort;
import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSaveUseCaseHandler implements UseCaseHandler<User, UserSave> {

    private final UserPort userPort;
    private final UserSavedEventPort userSavedEventPort;

    @Override
    public User handle(UserSave useCase) {
        log.info("Saving user {}", useCase);
        User savedUser = userPort.save(useCase.getFirstName(), useCase.getLastName());

        userSavedEventPort.publish(UserSavedEvent.from(useCase, savedUser.getId()));

        return savedUser;
    }
}
