package si.fri.prpo.skupina00.evcharging.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina00.evcharging.entities.City;
import si.fri.prpo.skupina00.evcharging.services.annotations.LogCalls;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@LogCalls
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

    public List<City> getCities(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(em, City.class, queryParameters);
    }

    public Long getCityCount(QueryParameters queryParameters) {
        return JPAUtils.queryEntitiesCount(em, City.class, queryParameters);
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
    public boolean updateCity(Integer id, City city) {
        City oldCity = getCity(id);
        city.setId(oldCity.getId());

        if (em.merge(city) != null) {
            log.info("Updated city " + city.getName());
            return true;
        }

        log.severe("Failed to update city");
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
