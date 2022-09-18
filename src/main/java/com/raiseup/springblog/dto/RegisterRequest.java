package com.raiseup.springblog.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
}
