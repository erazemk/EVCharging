package si.fri.prpo.skupina00.evcharging.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina00.evcharging.entities.Location;
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
public class LocationBean {
    private static final Logger log = Logger.getLogger(LocationBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + LocationBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + LocationBean.class.getSimpleName());
    }

    public List<Location> getLocations() {
        List<Location> locationList = em.createNamedQuery("Location.getAll",
                Location.class).getResultList();
        log.info("Query location list");
        return locationList;
    }

    public List<Location> getLocations(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(em, Location.class, queryParameters);
    }

    public Long getLocationCount(QueryParameters queryParameters) {
        return JPAUtils.queryEntitiesCount(em, Location.class, queryParameters);
    }

    public Location getLocation(Integer id) {
        Location location = em.find(Location.class, id);
        log.info("Query location " + location);
        return location;
    }

    @Transactional
    public Location addLocation(Location location) {
        if(location != null) {
            em.persist(location);
            log.info("Added location " + location);
            return location;
        }

        log.severe("Failed to add location");
        return null;
    }

    @Transactional
    public Location updateLocation(Integer id, Location location) {
        Location oldLocation = getLocation(id);
        location.setId(oldLocation.getId());

        if (em.merge(location) != null) {
            log.info("Updated location " + location);
            return location;
        }

        log.severe("Failed to update location " + location);
        return null;
    }

    @Transactional
    public boolean deleteLocation(Integer id) {
        Location location = getLocation(id);

        if(location != null){
            em.remove(location);
            log.config("Deleted location " + location);
            return true;
        }

        log.severe("Failed to delete location");
        return false;
    }
}
