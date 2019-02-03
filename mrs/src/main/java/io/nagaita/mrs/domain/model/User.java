package io.nagaita.mrs.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usr")
@Data
public class User implements Serializable {

    @Id
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

}
