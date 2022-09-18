package com.raiseup.springblog.service;

import com.raiseup.springblog.model.AppUser;
import com.raiseup.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser appUser = optionalUser.orElseThrow(() -> new UsernameNotFoundException("AppUser with username: "+username+" does not exist"));

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .accountExpired(appUser.getAccountExpired())
                .accountLocked(appUser.getAccountLocked())
                .credentialsExpired(appUser.getCredentialsExpired())
                .disabled(appUser.getDisabled())
                .authorities(getAuthorities("ROLE_USER"))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
