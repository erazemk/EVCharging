package si.fri.prpo.skupina00.evcharging.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina00.evcharging.entities.Charge;
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
public class ChargeBean {
    private static final Logger log = Logger.getLogger(ChargeBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + ChargeBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + ChargeBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    public List<Charge> getCharges() {
        List<Charge> chargeList = em.createNamedQuery("Charge.getAll", Charge.class)
                .getResultList();
        log.info("Queried charge list");
        return chargeList;
    }

    public List<Charge> getCharges(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(em, Charge.class, queryParameters);
    }

    public Long getChargeCount(QueryParameters queryParameters) {
        return JPAUtils.queryEntitiesCount(em, Charge.class, queryParameters);
    }

    public Charge getCharge(Integer id) {
        Charge charge = em.find(Charge.class, id);
        log.info("Queried charge info");
        log.config("Queried info for " + charge);
        return charge;
    }

    @Transactional
    public Charge addCharge(Charge charge) {
        if(charge != null){
            em.persist(charge);
            log.info("Added charge");
            log.config("Added charge " + charge);
            return charge;
        }

        log.severe("Failed to add charge");
        return null;
    }

    @Transactional
    public Charge updateCharge(Integer id, Charge charge) {
        Charge oldCharge = getCharge(id);
        charge.setId(oldCharge.getId());

        if (em.merge(charge) != null) {
            log.info("Updated charge");
            log.config("Updated charge " + charge);
            return charge;
        }

        log.severe("Failed to update charge");
        return null;
    }

    @Transactional
    public boolean deleteCharge(Integer id) {
        Charge charge = getCharge(id);

        if(charge != null){
            em.remove(charge);
            log.info("Deleted charge");
            log.config("Deleted charge " + charge);
        }

        log.severe("Failed to delete charge");
        return false;
    }
}
