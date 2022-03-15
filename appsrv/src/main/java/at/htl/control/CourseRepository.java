package at.htl.control;

import at.htl.entity.Course;
import at.htl.entity.Level;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class CourseRepository implements PanacheRepository<Course> {

    public List<Course> findCourseByLevel(Level level) {
        return find("level", level).list();
    }


    public Course save(Course course) {
        return getEntityManager().merge(course);
    }

}
