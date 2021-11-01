package si.fri.prpo.skupina00.storitve;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class UserBean {

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    public List getUsers() {
        Query query = em.createNamedQuery("User.getAll");
        return query.getResultList();
    }

}
