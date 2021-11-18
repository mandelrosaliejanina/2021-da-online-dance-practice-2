package at.htl.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "D_USAGE")
public class Usage extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UG_ID")
    public Long id;

    @JoinColumn(name = "UG_COURSE")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    public Course course;

    @JoinColumn(name = "UG_FILE")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
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
