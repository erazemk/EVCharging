package si.fri.prpo.skupina00.storitve;

import si.fri.prpo.skupina00.entitete.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Logger;

@ApplicationScoped
public class ManagingStations {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @Inject
    private UserBean userBean;

    @Inject
    private OwnerBean ownerBean;

    @Inject
    private CityBean cityBean;

    @PersistenceContext(unitName = "evcharging-jpa")
    private EntityManager em;

    public boolean addCharge(User u, Station s) {
        Charge c = new Charge(u, s, Time.valueOf(LocalTime.now()));
        return userBean.addCharge(c);
    }

    public boolean addReservation(User u, Station s) {
        Reservation r = new Reservation(u, s, Time.valueOf(LocalTime.now()));
        return userBean.addReservation(r);
    }

    public boolean addStation(String stationName, Owner owner, Time openTime, Time closeTime, Float price, Integer wattage, String adapterType) {
        Station s = new Station(stationName, owner, openTime, closeTime, price, wattage, adapterType);
        return ownerBean.createStation(s);
    }

    public boolean addCity(String name) {
        return cityBean.createCity(new City(name));
    }

}
