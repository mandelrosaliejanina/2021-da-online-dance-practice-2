package at.htl.control;

import at.htl.entity.Usage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsageRepository implements PanacheRepository<Usage> {

}
