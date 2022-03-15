package at.htl.control;

import at.htl.entity.AccessToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
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

        if (accessToken.getActivationDate() != null && accessToken.getActivationDate().isAfter(LocalDate.now())) {
            // not yet activated
            return false;
        }

        if (accessToken.getDaysValid() == null && accessToken.getExpireDate() == null && accessToken.getActivationDate() != null) {
            // infinite token when activation date is set
            return true;
        }

        if (accessToken.getDaysValid() != null && accessToken.getExpireDate() == null && accessToken.getActivationDate() != null) {
            // days valid is set
            return accessToken.getDaysValid() >= 0 && accessToken.getActivationDate().plusDays(accessToken.getDaysValid()).isAfter(LocalDate.now());
        }

        if (accessToken.getDaysValid() == null && accessToken.getExpireDate() != null && accessToken.getActivationDate() != null) {
            // expire date is set
            return accessToken.getActivationDate().isBefore(LocalDate.now()) && accessToken.getExpireDate().isAfter(LocalDate.now());
        }

        if (accessToken.getDaysValid() != null && accessToken.getExpireDate() != null && accessToken.getActivationDate() != null) {
            // all three are set
            return accessToken.getDaysValid() >= 0
                    && accessToken.getActivationDate().plusDays(accessToken.getDaysValid()).isAfter(LocalDate.now())
                    && accessToken.getActivationDate().plusDays(accessToken.getDaysValid()).isBefore(accessToken.getExpireDate());
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

}
