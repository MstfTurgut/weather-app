package com.mstftrgt.user_api.infra.adapters.user.rest;

import com.mstftrgt.user_api.domain.common.usecase.UseCaseHandler;
import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.usecase.UserRetrieve;
import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import com.mstftrgt.user_api.infra.adapters.user.rest.dto.UserResponse;
import com.mstftrgt.user_api.infra.adapters.user.rest.dto.UserSaveRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UseCaseHandler<User, UserSave> userSaveUseCaseHandler;
    private final UseCaseHandler<User, UserRetrieve> userRetrieveUseCaseHandler;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveUser(@RequestBody UserSaveRequest userSaveRequest) {
        User user = userSaveUseCaseHandler.handle(userSaveRequest.toUseCase());
        return UserResponse.from(user);
    }

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse retrieveUser(@RequestParam String firstName, @RequestParam String lastName) {
        User user = userRetrieveUseCaseHandler.handle(new UserRetrieve(firstName, lastName));
        return UserResponse.from(user);
    }
}
