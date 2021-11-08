package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.Station;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class OwnerBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized owner bean");
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed owner bean");
    }

    @Transactional
    public boolean createStation(Station s) {
        if(s != null) {
            em.persist(s);
            log.info("Created station");
            log.config("Created station" + s);
            return true;
        }

        log.severe("Failed to create station");
        return false;
    }

    @Transactional
    public boolean updateStation(int id, Station stationNew) {
        Station s = em.find(Station.class, id);

        stationNew.setId(s.getId());
        em.merge(stationNew);

        return true;
    }

    @Transactional
    public boolean deleteStation(int id) {
        Station s = em.find(Station.class, id);
        if(s != null){
            em.remove(s);
            log.info("Deleted station");
            log.config("Deleted station" + s);
            return true;
        }
        log.severe("Delete station failed");
        return false;
    }
}
