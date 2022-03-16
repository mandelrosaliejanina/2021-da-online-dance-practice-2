package at.htl.control;

import at.htl.entity.D_File;
import at.htl.entity.Usage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class UsageRepository implements PanacheRepository<Usage> {

    public List<D_File> findFilesByCourseId(Long courseId) {
        TypedQuery<D_File> typedQuery = getEntityManager()
                .createNamedQuery("Usage.findByCourseId", D_File.class)
                .setParameter("ID", courseId);
        return typedQuery.getResultList();
    }

    public void deleteUsageByFileId(Long fileId) {
        Query typedQuery = getEntityManager()
                .createQuery("delete from Usage u where u.file.id = :ID")
                .setParameter("ID", fileId);
         typedQuery.executeUpdate();
    }

    public boolean usageExistsInFile(Long fileId) {
        TypedQuery<Long> typedQuery = getEntityManager()
                .createNamedQuery("Usage.usageExistsInFile",Long.class)
                .setParameter("ID", fileId);
        return typedQuery.getSingleResult() > 0;
    }

    public void deleteUsageByCourseId(Long courseId) {
        Query typedQuery = getEntityManager()
                .createQuery("delete from Usage u where u.course.id = :ID")
                .setParameter("ID", courseId);
        typedQuery.executeUpdate();
    }

    public boolean usageExistsInCourse(Long courseId) {
        TypedQuery<Long> typedQuery = getEntityManager()
                .createNamedQuery("Usage.usageExistsInCourse",Long.class)
                .setParameter("ID", courseId);
        return typedQuery.getSingleResult() > 0;
    }
}
