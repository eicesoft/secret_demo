package org.secretdemo.domain.dto;

import lombok.Data;

@Data
public class UserDto {
    Long id;

    String username;

    String realName;

    String email;
}
