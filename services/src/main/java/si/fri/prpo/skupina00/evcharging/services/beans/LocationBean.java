package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.Location;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
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

    public Location getLocation(Integer id) {
        Location location = em.find(Location.class, id);
        log.info("Query location " + location);
        return location;
    }

    @Transactional
    public boolean addLocation(Location location) {
        if(location != null) {
            em.persist(location);
            log.info("Added location " + location);
            return true;
        }

        log.severe("Failed to add location");
        return false;
    }

    @Transactional
    public boolean updateLocation(Integer id, Location location) {
        Location oldLocation = getLocation(id);
        location.setId(oldLocation.getId());

        if (em.merge(location) != null) {
            log.info("Updated location " + location);
            return true;
        }

        log.severe("Failed to update location " + location);
        return false;
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
