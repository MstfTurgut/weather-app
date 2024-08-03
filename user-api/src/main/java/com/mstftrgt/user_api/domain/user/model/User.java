package com.mstftrgt.user_api.domain.user.model;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
}
