package com.mstftrgt.user_api.integration;

import com.mstftrgt.user_api.domain.user.model.User;
import com.mstftrgt.user_api.infra.adapters.user.jpa.UserDataAdapter;
import com.mstftrgt.user_api.infra.adapters.user.jpa.entity.UserEntity;
import com.mstftrgt.user_api.infra.adapters.user.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserCacheIT {

    @MockBean
    private UserRepository mockUserRepository;

    @Autowired
    private UserDataAdapter userDataAdapter;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenRetrieveUser_thenUserReturnedFromCache() {
        UserEntity userEntity = new UserEntity(1L, "firstName", "lastName");
        given(mockUserRepository.findByFirstNameAndLastName("firstName", "lastName"))
                .willReturn(Optional.of(userEntity));

        User userCacheMiss = userDataAdapter.retrieveByFirstNameAndLastName("firstName", "lastName");
        User userCacheHit = userDataAdapter.retrieveByFirstNameAndLastName("firstName", "lastName");

        assertThat(userCacheMiss).isEqualTo(userEntity.toModel());
        assertThat(userCacheHit).isEqualTo(userEntity.toModel());

        verify(mockUserRepository, times(1)).findByFirstNameAndLastName("firstName", "lastName");
        assertThat(userFromCache()).isEqualTo(userEntity.toModel());
    }

    private Object userFromCache() {
        return cacheManager.getCache("userCache").get("firstName:lastName").get();
    }
}