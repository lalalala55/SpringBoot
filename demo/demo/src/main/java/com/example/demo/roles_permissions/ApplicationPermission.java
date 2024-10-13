package com.example.demo.roles_permissions;

public enum ApplicationPermission {
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
