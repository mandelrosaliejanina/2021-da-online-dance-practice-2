package at.htl.control;

import at.htl.entity.D_File;
import at.htl.entity.Usage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
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

}
