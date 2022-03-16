package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "D_ACCESS_TOKEN")
@NamedQueries({
        @NamedQuery(
                name = "AccessToken.accessTokenExistsInCourse",
                query = "select count(a) from AccessToken a where a.course.id = :ID"
        ),
        @NamedQuery(
                name = "AccessToken.accessTokenExists",
                query = "select count(a) from AccessToken a where a.course.id = :ID"
        )
})
public class AccessToken extends PanacheEntityBase {

    @Id
    public String token;

    @ManyToOne
    @JoinColumn(name = "AT_COURSE")
    public Course course;

    @Column(name = "AT_ACTIVATION_DATE")
    public LocalDate activationDate;

    @Column(name = "AT_EXPIRE_DATE")
    public LocalDate expireDate;

    public AccessToken(Course course, LocalDate expireDate) {
        this();
        this.course = course;
        this.expireDate = expireDate;
    }

    public AccessToken(Course course) {
        this();
        this.course = course;
    }

    public AccessToken() {
        this.token = RandomStringUtils.randomAlphabetic(5);
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
