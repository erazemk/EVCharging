package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.*;
import si.fri.prpo.skupina00.evcharging.services.dtos.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@RequestScoped
public class StationManagerBean {
    private static final Logger log = Logger.getLogger(StationManagerBean.class.getName());

    @Inject
    private UserBean userBean;

    @Inject
    private CityBean cityBean;

    @Inject
    private ChargeBean chargeBean;

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private StationBean stationBean;

    @Inject
    private LocationBean locationBean;

    @Inject
    private OwnerBean ownerBean;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + StationManagerBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + StationManagerBean.class.getSimpleName());
    }

    @Transactional
    public boolean addCharge(ChargeDto chargeDto) {
        User user = userBean.getUser(chargeDto.getUserId());
        Station station = stationBean.getStation(chargeDto.getStationId());

        if (user == null) {
            log.warning("Could not add charge, user does not exist");
        }

        if (station == null) {
            log.warning("Could not add charge, station does not exist");
        }

        Charge charge = new Charge(user, station);
        return chargeBean.addCharge(charge);
    }

    public boolean addReservation(ReservationDto reservationDto) {
        User user = userBean.getUser(reservationDto.getUserId());
        Station station = stationBean.getStation(reservationDto.getStationId());

        if (user == null) {
            log.warning("Could not add reservation, user does not exist");
        }

        if (station == null) {
            log.warning("Could not add reservation, station does not exist");
        }

        Reservation reservation = new Reservation(user, station);
        return reservationBean.addReservation(reservation);
    }

    public boolean addStation(StationDto stationDto) {
        Owner owner = ownerBean.getOwner(stationDto.getOwnerId());
        Location location = locationBean.getLocation(stationDto.getLocationId());

        if (owner == null) {
            log.warning("Could not add station, owner does not exist");
        }

        if (location == null) {
            log.warning("Could not add station, location does not exist");
        }

        Station station = new Station(stationDto.getName(), owner, stationDto.getOpenTime(), stationDto.getCloseTime(),
                stationDto.getPrice(), stationDto.getWattage(), stationDto.getAdapterType(), location);
        return stationBean.addStation(station);
    }

    public boolean addLocation(LocationDto locationDto) {
        City city = cityBean.getCity(locationDto.getCityId());
        Location location = new Location(city, locationDto.getAddress(), locationDto.getXCoordinate(),
                locationDto.getYCoordinate());
        return locationBean.addLocation(location);
    }

    public boolean addCity(CityDto cityDto) {
        City city = new City(cityDto.getName());
        return cityBean.addCity(city);
    }

}
