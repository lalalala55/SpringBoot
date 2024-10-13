package com.example.demo.roles_permissions;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.roles_permissions.ApplicationPermission.*;

import static com.example.demo.roles_permissions.ApplicationPermission.*;

public enum ApplicationRole {
    ADMIN(new HashSet<>(Arrays.asList(COURSE_READ, COURSE_WRITE, USER_READ, USER_WRITE))),
    USER(new HashSet<>(Arrays.asList(COURSE_READ)));

    private Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorites(){
        Set<SimpleGrantedAuthority>  authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
