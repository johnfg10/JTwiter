package io.github.johnfg10.jtweeter.database.repositories;

import io.github.johnfg10.jtweeter.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
