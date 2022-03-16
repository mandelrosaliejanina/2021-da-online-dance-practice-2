package at.htl.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "D_USAGE")
@NamedQueries({
        @NamedQuery(
                name = "Usage.findByCourseId",
                query = "select u.file from Usage u where u.course.id = :ID"
        ),
        @NamedQuery(
                name = "Usage.usageExistsInCourse",
                query = "select count(u) from Usage u where u.course.id = :ID"
        ),
        @NamedQuery(
                name = "Usage.usageExistsInFile",
                query = "select count(u) from Usage u where u.file.id = :ID"
        )
})
public class Usage extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UG_ID")
    public Long id;

    @JoinColumn(name = "UG_COURSE")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public Course course;

    @JoinColumn(name = "UG_FILE")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public D_File file;


    //region constructor

    public Usage(Course course, D_File file) {
        this.course = course;
        this.file = file;
    }

    public Usage() {
    }

    //endregion


    @Override
    public String toString() {
        return "Usage{" +
                "id=" + id +
                ", course=" + course +
                ", file=" + file +
                '}';
    }
}
