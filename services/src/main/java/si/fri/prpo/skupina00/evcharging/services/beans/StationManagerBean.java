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
    private StationLocationBean stationLocationBean;

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
        StationLocation stationLocation = stationLocationBean.getStationLocation(stationDto.getStationLocationId());

        if (owner == null) {
            log.warning("Could not add station, owner does not exist");
        }

        if (stationLocation == null) {
            log.warning("Could not add station, station location does not exist");
        }

        Station station = new Station(stationDto.getStationName(), owner, stationDto.getOpenTime(),
                stationDto.getCloseTime(), stationDto.getPrice(), stationDto.getWattage(), stationDto.getAdapterType(),
                stationLocation);
        return stationBean.addStation(station);
    }

    public boolean addStationLocation(StationLocationDto stationLocationDto) {
        City city = cityBean.getCity(stationLocationDto.getCityId());
        StationLocation stationLocation = new StationLocation(city, stationLocationDto.getAddress(),
                stationLocationDto.getXCoordinate(), stationLocationDto.getYCoordinate());
        return stationLocationBean.addStationLocation(stationLocation);
    }

    public boolean addCity(CityDto cityDto) {
        City city = new City(cityDto.getName());
        return cityBean.addCity(city);
    }

}
