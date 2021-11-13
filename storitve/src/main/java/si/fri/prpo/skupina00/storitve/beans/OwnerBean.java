package si.fri.prpo.skupina00.storitve.beans;

import si.fri.prpo.skupina00.entitete.Owner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

public class OwnerBean {
    private static final Logger log = Logger.getLogger(OwnerBean.class.getName());

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + OwnerBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + OwnerBean.class.getSimpleName());
    }

    public List<Owner> getOwners() {
        List<Owner> ownerList = em.createNamedQuery("Owner.getAll", Owner.class)
                .getResultList();
        log.info("Queried owner list");
        return ownerList;
    }

    public Owner getOwner(Integer id) {
        Owner owner = em.find(Owner.class, id);
        log.info("Queried owner info");
        log.config("Queried " + owner + "'s info");
        return owner;
    }

    @Transactional
    public boolean addOwner(Owner owner) {
        if (owner != null) {
            em.persist(owner);
            log.info("Created owner");
            log.config("Created owner " + owner);
            return true;
        }

        log.severe("Failed to create owner");
        return false;
    }

    @Transactional
    public boolean updateOwner(Integer id, Owner owner) {
        Owner oldOwner = em.find(Owner.class, id);
        owner.setId(oldOwner.getId());
        if (em.merge(owner) != null) {
            log.info("Updated owner");
            log.config("Updated owner " + owner);
            return true;
        }

        log.severe("Failed to update owner " + oldOwner);
        return false;
    }

    @Transactional
    public boolean deleteOwner(Integer id) {
        Owner owner = em.find(Owner.class, id);

        if (owner != null) {
            em.remove(owner);
            log.info("Deleted owner");
            log.config("Deleted owner " + owner);
            return true;
        }

        log.severe("Failed to delete owner " + id);
        return false;
    }

    /*
    public List<Station> getOwnedStations(Integer id) {
        Owner owner = em.find(Owner.class, id);

        if (owner != null) {
            List<Station> ownedStations = owner.getOwnedStations();
            log.info("Queried owned stations");
            log.config("Queried " + owner + "'s owned stations");
            return ownedStations;
        }

        log.severe("Failed to find owner " + id);
        return null;
    }
    */
}
