package io.nagaita.mrs.domain.repository.user;

import io.nagaita.mrs.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
