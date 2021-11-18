package at.htl.control;

import at.htl.entity.Level;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelRepository implements PanacheRepositoryBase<Level, String> {

}
