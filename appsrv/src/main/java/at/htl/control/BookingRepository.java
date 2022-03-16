package at.htl.control;

import at.htl.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BookingRepository implements PanacheRepository<Booking> {

    public void deleteBookingByCourseId(Long courseId) {
        Query typedQuery = getEntityManager()
                .createQuery("delete from Booking b where b.course.id = :ID")
                .setParameter("ID", courseId);
        typedQuery.executeUpdate();
    }

    public boolean bookingExistsInCourse(Long courseId) {
        TypedQuery<Long> typedQuery = getEntityManager()
                .createNamedQuery("Booking.bookingExistsInCourse",Long.class)
                .setParameter("ID", courseId);
        return typedQuery.getSingleResult() > 0;
    }
}
