package io.github.johnfg10.jtweeter.database.repositories;

import io.github.johnfg10.jtweeter.database.model.Twit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwitRepository extends JpaRepository<Twit, Long> {
    Optional<Twit> findById(int id);
}
