package com.mstftrgt.user_api.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
}
