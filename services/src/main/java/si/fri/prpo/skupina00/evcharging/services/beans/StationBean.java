package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.Station;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class StationBean {
    private static final Logger log = Logger.getLogger(StationBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + StationBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + StationBean.class.getSimpleName());
    }

    public List<Station> getStations() {
        List<Station> stations = em.createNamedQuery("Station.getAll", Station.class)
                .getResultList();
        log.info("Query stations");
        return stations;
    }

    public Station getStation(int id) {
        Station station = em.find(Station.class, id);
        log.info("Query station " + station);
        return station;
    }

    @Transactional
    public boolean addStation(Station station) {
        if(station != null) {
            em.persist(station);
            log.info("Created station");
            log.config("Created station " + station.getName());
            return true;
        }

        log.severe("Failed to create station");
        return false;
    }

    @Transactional
    public boolean updateStation(Integer id, Station station) {
        Station oldStation = getStation(id);
        station.setId(oldStation.getId());

        if (em.merge(station) != null) {
            log.info("Updated station " + station.getName());
            return true;
        }

        log.severe("Failed to update station");
        return false;
    }

    @Transactional
    public boolean deleteStation(Station station) {
        if(station != null){
            em.remove(station);
            log.info("Deleted station" + station.getName());
            return true;
        }

        log.severe("Failed to delete station");
        return false;
    }
}
