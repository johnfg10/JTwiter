package io.github.johnfg10.jtweeter.database.repositories;

import io.github.johnfg10.jtweeter.database.model.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementsRepository extends CrudRepository<Announcement, Integer> {
    Announcement findById(long id);
}
