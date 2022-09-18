package com.raiseup.springblog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String content;

    private Instant createOn;
    private Instant updateOn;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
