package si.fri.prpo.skupina00.storitve.beans;

import si.fri.prpo.skupina00.entitete.City;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class CityBean {
    private static final Logger log = Logger.getLogger(CityBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + CityBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + CityBean.class.getSimpleName());
    }

    public List<City> getCities() {
        List<City> stations = em.createNamedQuery("City.getAll", City.class)
                .getResultList();
        log.info("Query cities list");
        return stations;
    }

    public City getCity(Integer id) {
        City city = em.find(City.class, id);
        log.info("Query city " + city.getName());
        return city;
    }

    @Transactional
    public boolean addCity(City city) {
        if(city != null) {
            em.persist(city);
            log.info("Added city " + city.getName());
            return true;
        }

        log.severe("Failed to add city");
        return false;
    }

    @Transactional
    public boolean deleteCity(Integer id) {
        City city = this.getCity(id);

        if(city != null){
            em.remove(city);
            log.config("Deleted city " + city.getName());
            return true;
        }

        log.severe("Failed to delete city");
        return false;
    }
}