package com.raiseup.springblog.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PostDto {
    private Long id;
    private String content;
    private String title;
    private String username;
}
