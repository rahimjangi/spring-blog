package com.raiseup.springblog.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Slf4j
public class RegisterResponse {

    private String username;
    private String email;
}
