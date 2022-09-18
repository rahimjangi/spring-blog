package com.raiseup.springblog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    private Boolean accountExpired=false;
    private Boolean accountLocked=false;
    private Boolean credentialsExpired=false;
    private Boolean disabled=false;

    @NotBlank
    private String email;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "appUser")
    private List<Post>posts;
}
