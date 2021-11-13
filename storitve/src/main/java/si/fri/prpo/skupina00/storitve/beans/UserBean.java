package si.fri.prpo.skupina00.storitve.beans;

import si.fri.prpo.skupina00.entitete.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class UserBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + UserBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + UserBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    public List<User> getUsers() {
        List<User> users = em.createNamedQuery("User.getAll", User.class)
                .getResultList();
        log.info("Queried user list");
        return users;
    }

    public User getUser(Integer id) {
        User user = em.find(User.class, id);
        log.info("Queried user info");
        log.config("Queried " + user + "'s info");
        return user;
    }

    @Transactional
    public boolean addUser(User user) {
        if (user != null) {
            em.persist(user);
            log.info("Created user");
            log.config("Created user " + user);
            return true;
        }

        log.severe("Failed to create user");
        return false;
    }

    @Transactional
    public boolean updateUser(Integer id, User user) {
        User oldUser = em.find(User.class, id);
        user.setId(oldUser.getId());
        if (em.merge(user) != null) {
            log.info("Updated user");
            log.config("Updated user " + user);
            return true;
        }

        log.severe("Failed to update user " + oldUser);
        return false;
    }

    @Transactional
    public boolean deleteUser(Integer id) {
        User user = em.find(User.class, id);

        if (user != null) {
            em.remove(user);
            log.info("Deleted user");
            log.config("Deleted user " + user);
            return true;
        }

        log.severe("Failed to delete user " + id);
        return false;
    }
}
