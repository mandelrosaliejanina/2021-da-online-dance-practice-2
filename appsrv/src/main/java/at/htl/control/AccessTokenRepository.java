package at.htl.control;

import at.htl.entity.AccessToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;

@ApplicationScoped
@Transactional
public class AccessTokenRepository implements PanacheRepository<AccessToken> {

    public AccessToken save(AccessToken accessToken) {
        return getEntityManager().merge(accessToken);
    }

    public boolean validate(AccessToken accessToken) {
        if (accessToken == null) {
            return false;
        }

        if (accessToken.getActivationDate().isAfter(LocalDate.now())) {
            // not yet activated
            return false;
        }

        if (accessToken.getExpireDate() == null && accessToken.getActivationDate() != null) {
            // infinite token when activation date is set
            return true;
        }

        if (accessToken.getExpireDate() != null && accessToken.getActivationDate() != null) {
            // expire date is set
            return accessToken.getActivationDate().isBefore(LocalDate.now())
                    && accessToken.getExpireDate().isAfter(LocalDate.now());
        }

        return false;
    }

    @Transactional
    public AccessToken activateToken(AccessToken accessToken) {
        if (accessToken.activationDate != null) {
            return accessToken;
        }

        accessToken.activationDate = LocalDate.now();
        return getEntityManager().merge(accessToken);
    }

    public void deleteAccessTokenByCourseId(Long courseId) {
        Query typedQuery = getEntityManager()
                .createQuery("delete from AccessToken a where a.course.id = :ID")
                .setParameter("ID", courseId);
        typedQuery.executeUpdate();
    }

    public boolean accessTokenExistsInCourse(Long courseId) {
        TypedQuery<Long> typedQuery = getEntityManager()
                .createNamedQuery("AccessToken.accessTokenExistsInCourse", Long.class)
                .setParameter("ID", courseId);
        return typedQuery.getSingleResult() > 0;
    }

}
