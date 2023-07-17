package com.bcbsm.ex4.service;

import com.bcbsm.ex4.entity.Role;
import com.bcbsm.ex4.entity.UserEntity;
import com.bcbsm.ex4.repository.UserRepository;
import com.bcbsm.ex4.service.interfaces.UserServiceWrapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService implements UserServiceWrapper, UserDetailsService {
    final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public Collection<GrantedAuthority> mapRolesToAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No UserEntity Found"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), mapRolesToAuthorities(userEntity.getRole()));
    }
}
