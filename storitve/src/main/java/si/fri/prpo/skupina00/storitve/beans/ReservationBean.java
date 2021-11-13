package si.fri.prpo.skupina00.storitve.beans;

import si.fri.prpo.skupina00.entitete.Reservation;
import si.fri.prpo.skupina00.entitete.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

public class ReservationBean {
    private static final Logger log = Logger.getLogger(ReservationBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + ReservationBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + ReservationBean.class.getSimpleName());
    }

    public List<Reservation> getReservations(Integer id) {
        List<Reservation> reservations = em.createNamedQuery("Reservation.getAll", Reservation.class)
                .getResultList();
        log.info("Query reservations");
        return reservations;
    }

    @Transactional
    public boolean addReservation(Reservation reservation) {
        if(reservation != null){
            em.persist(reservation);
            log.info("Created reservation");
            log.config("Created reservation " + reservation);
            return true;
        }

        log.severe("Failed to add reservation");
        return false;
    }

    @Transactional
    public boolean deleteReservation(Reservation reservation) {
        if(reservation != null){
            em.remove(reservation);
            log.info("Deleted reservation");
            log.config("Deleted reservation " + reservation);
            return true;
        }

        log.severe("Failed to delete reservation");
        return false;
    }
}
