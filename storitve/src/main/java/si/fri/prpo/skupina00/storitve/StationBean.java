package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.Station;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

public class StationBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized user bean");
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed user bean");
    }

    public List<Station> getAll() {
        List<Station> stations = em.createNamedQuery("Station.getAll", Station.class)
                .getResultList();
        log.info("Query: get all stations");
        return stations;
    }

    public Station getStation(int id) {
        Station station = em.find(Station.class, id);
        log.info("Query: get id specific station");
        log.config("Got station:" + station);
        return station;
    }
}
