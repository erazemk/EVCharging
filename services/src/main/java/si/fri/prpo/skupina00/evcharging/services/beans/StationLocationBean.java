package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.StationLocation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class StationLocationBean {
    private static final Logger log = Logger.getLogger(StationLocationBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + StationLocationBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + StationLocationBean.class.getSimpleName());
    }

    public List<StationLocation> getStationLocations() {
        List<StationLocation> stationLocationList = em.createNamedQuery("StationLocation.getAll",
                StationLocation.class).getResultList();
        log.info("Query station location list");
        return stationLocationList;
    }

    public StationLocation getStationLocation(Integer id) {
        StationLocation stationLocation = em.find(StationLocation.class, id);
        log.info("Query station location " + stationLocation);
        return stationLocation;
    }

    @Transactional
    public boolean addStationLocation(StationLocation stationLocation) {
        if(stationLocation != null) {
            em.persist(stationLocation);
            log.info("Added station location " + stationLocation);
            return true;
        }

        log.severe("Failed to add station location");
        return false;
    }

    @Transactional
    public boolean updateStationLocation(Integer id, StationLocation stationLocation) {
        StationLocation oldStationLocation = getStationLocation(id);
        stationLocation.setId(oldStationLocation.getId());

        if (em.merge(stationLocation) != null) {
            log.info("Updated station location " + stationLocation);
            return true;
        }

        log.severe("Failed to update station location " + stationLocation);
        return false;
    }

    @Transactional
    public boolean deleteStationLocation(Integer id) {
        StationLocation stationLocation = getStationLocation(id);

        if(stationLocation != null){
            em.remove(stationLocation);
            log.config("Deleted station location " + stationLocation);
            return true;
        }

        log.severe("Failed to delete station location");
        return false;
    }
}
