package io.github.johnfg10.jtweeter.database.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/*
@Entity
@Data
@Builder
@Table(name = "users_info")
public class AdditonalUserInfo {

    @Column
    @Size(min = 1, max = 200, message = "Please use your real age")
    public Integer age;

    @Column
    @Size(min = 1, max = 50, message = "Please use a maximum of 50 characters for your name")
    public String firstname;

    @Column
    @Size(min = 1, max = 50, message = "Please use a maximum of 50 characters for your surname")
    public String surname;

    @Column
    public String bio;

    @Column
    public String logo;

    @OneToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    @EmbeddedId
    public User user;

    @Column(name = "creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;

    @Column(name = "creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date updateDate;
}
*/
