package com.odon.beprepared.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String phone;
    private String deviceId;
    private Long cityId;

}
