package com.bcbsm.ex4.service.interfaces;

import com.bcbsm.ex4.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserServiceWrapper {
    Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles);

    Collection<GrantedAuthority> mapRolesToAuthorities(String role);
}
