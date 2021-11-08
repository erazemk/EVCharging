package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.Owner;
import si.fri.prpo.skupina00.entitete.Station;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
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

    public List<Owner> getOwners() {
        List<Owner> owners = em.createNamedQuery("Owner.getAll", Owner.class)
                .getResultList();
        log.info("Queried owner list");
        return owners;
    }

    public Owner getOwner(Integer id) {
        Owner owner = em.find(Owner.class, id);
        log.info("Queried owner info");
        log.config("Queried " + owner + "'s info");
        return owner;
    }

    public List<Station> getOwnedStations(Integer id) {
        Owner o = em.find(Owner.class, id);

        if (o != null) {
            List<Station> ownedStations = o.getOwnedStations();
            log.info("Queried owned stations");
            log.config("Queried " + o + "'s owned stations");
            return ownedStations;
        }

        log.severe("Failed to find owner " + id);
        return null;
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
    public boolean updateStation(Integer id, Station stationNew) {
        Station s = em.find(Station.class, id);

        stationNew.setId(s.getId());
        em.merge(stationNew);

        return true;
    }

    @Transactional
    public boolean deleteStation(Integer id) {
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
