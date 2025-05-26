package com.ranpo.ranpobackend.member.domain.enums;

public enum MemberRole {
    USER,
    ADMIN;

    public String toRoleName() {
        return "ROLE_" + this.name(); // Spring Security "ROLE_" prefix 관례
    }
}
