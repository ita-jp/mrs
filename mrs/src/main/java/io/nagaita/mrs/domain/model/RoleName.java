package io.nagaita.mrs.domain.model;

public enum RoleName {
    ADMIN, USER;

    public String toSpringRoleName() {
        return "ROLE_" + name();
    }
}
