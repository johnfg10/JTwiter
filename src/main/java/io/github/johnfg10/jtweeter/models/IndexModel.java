package io.github.johnfg10.jtweeter.models;

import io.github.johnfg10.jtweeter.database.model.Announcement;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class IndexModel {
    Set<Announcement> announcements;
}
