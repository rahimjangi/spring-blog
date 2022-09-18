package com.raiseup.springblog.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
