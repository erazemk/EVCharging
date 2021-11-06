package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UserBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    public List<User> getUsers() {
        TypedQuery<User> tq = em.createNamedQuery("User.getAll", User.class);
        return tq.getResultList();
    }

    public List<User> getUsersCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> r = cq.from(User.class);
        CriteriaQuery<User> users = cq.select(r);
        TypedQuery<User> usersQuery = em.createQuery(users);
        return usersQuery.getResultList();
    }
}
