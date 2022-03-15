package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "D_COURSE")
public class Course extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    public Long id;

    @Column(name = "C_TITLE")
    public String title;

    @Column(name = "C_DESCR")
    public String description;

    @ManyToOne
    @JoinColumn(name = "C_LEVEL")
    public Level level;


    //region constructors
    public Course(String title, String description, Level level) {
        this.title = title;
        this.description = description;
        this.level = level;
    }

    public Course() {
    }
    //endregion


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", descr='" + description + '\'' +
                ", level=" + level +
                '}';
    }
}

