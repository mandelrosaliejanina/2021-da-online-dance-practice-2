package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "D_BOOKING")
public class Booking extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_ID")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "B_USERID")
    public User user;

    @ManyToOne
    @JoinColumn(name = "B_COURSEID")
    public Course course;

    //region constructors
    public Booking() {
    }

    public Booking(User user, Course course) {
        this.user = user;
        this.course = course;
    }
    //endregion

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", course=" + course +
                '}';
    }

}
