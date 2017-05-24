package apascualco.blog.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Table(name = "roles")
public class Rol {

    @Id
    @Getter
    @Setter
    @Column(name = "role_id")
    private int roleId;


    @Getter
    @Setter
    @Column(name = "rol")
    private String rol;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
