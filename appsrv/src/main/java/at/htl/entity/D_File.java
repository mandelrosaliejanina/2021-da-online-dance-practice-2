package at.htl.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "D_FILE")
public class D_File extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    public Long id;

    @Column(name = "F_NAME")
    public String name;

    @Column(name = "F_PATH")
    public String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_CONTENT_TYPE")
    public ContentType contentType;

    // region constructor


    public D_File(String name, String path, ContentType contentType) {
        this.name = name;
        this.path = path;
        this.contentType = contentType;
    }

    public D_File() {
    }

    //endregion


    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", contentType=" + contentType +
                '}';
    }
}
