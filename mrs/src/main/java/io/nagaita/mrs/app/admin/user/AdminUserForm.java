package io.nagaita.mrs.app.admin.user;

import lombok.Data;

@Data
public class AdminUserForm {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String roleName;
}
