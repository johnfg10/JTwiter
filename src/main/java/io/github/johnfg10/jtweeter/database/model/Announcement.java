package io.github.johnfg10.jtweeter.database.model;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Date;

@Data
@Builder
@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String title;
    public String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date creationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date expirationDate;
}
