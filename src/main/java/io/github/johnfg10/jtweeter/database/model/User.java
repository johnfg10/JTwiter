package io.github.johnfg10.jtweeter.database.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "users")
@Embeddable
public class User implements Serializable {

    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int id;

    @Column(name = "username", unique = true, nullable = false)
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "enabled")
    public boolean enabled;
}
