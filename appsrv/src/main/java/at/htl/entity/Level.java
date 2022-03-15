package at.htl.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "D_LEVEL")
public class Level extends PanacheEntityBase {

    @Id
    @Column(name = "L_ID")
    public String id;

    @Column(name = "L_DESCRIPTION")
    public String description;

    //region constructors
    public Level() {
    }

    public Level(String id, String description) {
        this.id = id;
        this.description = description;
    }

    //endregion

    @Override
    public String toString() {
        return "Level{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
