package io.nagaita.mrs.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum RoleName {
    ADMIN, USER;

    private static final List<String> ROLE_NAMES;
    private static final Map<String, RoleName> TO_ROLE;

    static {
        ROLE_NAMES = Arrays.stream(values()).map(RoleName::name).collect(Collectors.toList());
        TO_ROLE = Arrays.stream(values()).collect(Collectors.toMap(Enum::name, e -> e));
    }

    public static List<String> all() {
        return ROLE_NAMES;
    }

    public static RoleName lookup(String name) {
        return TO_ROLE.get(name);
    }

    public String toSpringRoleName() {
        return "ROLE_" + name();
    }
}
