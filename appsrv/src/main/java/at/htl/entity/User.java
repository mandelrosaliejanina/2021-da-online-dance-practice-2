package at.htl.entity;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;

@Entity
@Table(name = "D_USER")
@UserDefinition
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    public Long id;

    @Column(name = "U_USERNAME")
    @Username
    public String username;

    @Column(name = "U_FIRSTNAME")
    public String firstname;

    @Column(name = "U_LASTNAME")
    public String lastname;

    @Column(name = "U_PASSWORD")
    @Password
    public String password;

    @Column(name = "U_ROLE")
    @Roles
    public String role;

    //region constructors
    public User() {
    }

    public User(String username, String firstname, String lastname, String password, String role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role;
    }
    //endregion


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
