package com.mstftrgt.user_api.infra.adapters.user.jpa;

import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.domain.user.port.UserPort;
import com.mstftrgt.user_api.infra.adapters.user.jpa.entity.UserEntity;
import com.mstftrgt.user_api.infra.adapters.user.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public User save(String firstName, String lastName) {
        UserEntity userEntity = UserEntity
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return savedUserEntity.toModel();
    }

    @Override
    @Cacheable(value = "userCache", key = "#firstName + ':' + #lastName")
    public User retrieveByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow().toModel();
    }
}
