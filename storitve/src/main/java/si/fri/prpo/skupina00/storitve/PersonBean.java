package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class PersonBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Initialized user bean");
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed user bean");
    }

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @Transactional
    public boolean createPerson(Person p) {
        if (p != null) {
            em.persist(p);
            log.info("Created person");
            log.config("Created person " + p);
            return true;
        }

        log.severe("Failed to create person");
        return false;
    }

    @Transactional
    public boolean updatePerson(Integer id, Person p) {
        Person old = em.find(Person.class, id);
        p.setId(old.getId());
        if (em.merge(p) != null) {
            log.info("Updated person");
            log.config("Updated person " + p);
            return true;
        }

        log.severe("Failed to update person " + old);
        return false;
    }

    @Transactional
    public boolean deletePerson(Integer id) {
        Person p = em.find(Person.class, id);

        if (p != null) {
            em.remove(p);
            log.info("Deleted person");
            log.config("Deleted person " + p);
            return true;
        }

        log.severe("Failed to delete person " + id);
        return false;
    }
}
