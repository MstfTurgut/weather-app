package com.mstftrgt.user_api.integration;

import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.port.UserPort;
import com.mstftrgt.user_api.domain.user.usecase.UserSave;
import com.mstftrgt.user_api.infra.adapters.user.jpa.UserDataAdapter;
import com.mstftrgt.user_api.infra.adapters.user.jpa.entity.UserEntity;
import com.mstftrgt.user_api.infra.adapters.user.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserDataAdapterIT{

    @Autowired
    UserDataAdapter userDataAdapter;

    @Autowired
    UserRepository userRepository;

    @Test
    void should_save_user() {
        User savedUser = userDataAdapter.save("testFirstName", "testLastName");

        Optional<UserEntity> userEntity = userRepository.findById(savedUser.getId());
        assertThat(userEntity).isPresent();
        assertThat(userEntity.get().toModel()).isEqualTo(savedUser);
    }
}
