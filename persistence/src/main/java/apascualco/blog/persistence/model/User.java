package apascualco.blog.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int userId;

    @Basic
    @Getter
    @Setter
    @Column(name = "username")
    private String username;

    @Basic
    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Basic
    @Getter
    @Setter
    @Column(name = "enabled")
    private boolean enabled;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    private List<Rol> roles;

}
