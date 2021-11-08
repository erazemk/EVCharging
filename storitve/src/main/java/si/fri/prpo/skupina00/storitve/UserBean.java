package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.Charge;
import si.fri.prpo.skupina00.entitete.Reservation;
import si.fri.prpo.skupina00.entitete.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UserBean {
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
    public boolean addCharge(Charge chr) {
        if(chr != null){
            em.persist(chr);
            log.info("Created charge");
            log.config("Created charge" + chr);
        }
        log.severe("Failed to add charge");
        return false;
    }

    @Transactional
    public boolean deleteCharge(Charge chr) {
        if(chr != null){
            em.remove(chr);
            log.info("Deleted charge");
            log.config("Deleted charge" + chr);
        }
        log.severe("Failed to delete charge");
        return false;
    }

    public List<Reservation> getReservations(Integer id){
        List<Reservation> reservations = em.createNamedQuery("Reservation.getAll", Reservation.class)
                .getResultList();
        log.info("Query: get all reservations for user");
        return reservations;
    }

    @Transactional
    public boolean addReservation(Reservation r) {
        if(r != null){
            em.persist(r);
            log.info("Created reservation");
            log.config("Created reservation" + r);
            return true;
        }
        log.severe("Failed to add reservation");
        return false;
    }

    @Transactional
    public boolean deleteReservation(Reservation r) {
        if(r != null){
            em.remove(r);
            log.info("Deleted reservation");
            log.config("Deleted reservation" + r);
            return true;
        }
        log.severe("Failed to delete reservation");
        return false;
    }
}
