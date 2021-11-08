package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.City;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

public class CityBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized city bean");
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed city bean");
    }

    public List<City> getAll() {
        List<City> stations = em.createNamedQuery("City.getAll", City.class)
                .getResultList();
        log.info("Query: get all cities");
        return stations;
    }

    public City getCity(Integer id) {
        City city = em.find(City.class, id);
        log.info("Query: get id specific city");
        log.config("Got city:" + city);
        return city;
    }

    @Transactional
    public boolean createCity(City c) {
        if(c != null) {
            em.persist(c);
            log.info("Created city");
            log.config("Created city" + c);
            return true;
        }

        log.severe("Failed to create city");
        return false;
    }

    @Transactional
    public boolean deleteCity(Integer id) {
        City c = getCity(id);
        if(c != null){
            em.remove(c);
            log.info("Deleted city");
            log.config("Deleted city" + c);
            return true;
        }
        log.severe("Delete city failed");
        return false;
    }
}
