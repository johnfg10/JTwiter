package io.github.johnfg10.jtweeter.database.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "twits")
public class Twit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "content")
    public String content;


/*    @ManyToOne(targetEntity = io.github.johnfg10.jtweeter.database.model.User.class, cascade=CascadeType.ALL)
    public io.github.johnfg10.jtweeter.database.model.User author;*/

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public io.github.johnfg10.jtweeter.database.model.User author;
//CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
    @Column(name = "creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;

    @Column(name = "timestamp_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date updateDate;
}
