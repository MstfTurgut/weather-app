package com.mstftrgt.user_api.domain.user.port;

import com.mstftrgt.user_api.domain.user.model.User;

public interface UserPort {

    User save(String firstName, String lastName);

}
