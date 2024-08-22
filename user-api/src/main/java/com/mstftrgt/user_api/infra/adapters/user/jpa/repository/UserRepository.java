package com.mstftrgt.user_api.infra.adapters.user.jpa.repository;

import com.mstftrgt.user_api.infra.adapters.user.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
