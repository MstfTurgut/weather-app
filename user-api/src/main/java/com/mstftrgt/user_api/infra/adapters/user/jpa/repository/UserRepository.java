package com.mstftrgt.user_api.infra.adapters.user.jpa.repository;

import com.mstftrgt.user_api.infra.adapters.user.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
