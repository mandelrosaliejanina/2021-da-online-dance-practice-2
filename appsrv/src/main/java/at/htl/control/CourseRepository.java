package at.htl.control;

import at.htl.entity.Course;
import at.htl.entity.Level;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {

    public List<Course> findCourseByLevel(Level level) {
        return find("id", level.id).list();
    }

}
