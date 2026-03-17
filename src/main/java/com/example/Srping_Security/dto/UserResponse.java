package com.example.Srping_Security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userPassword")
    private String userPassword;

    @JsonProperty("userRole")
    private String userRole;
}
